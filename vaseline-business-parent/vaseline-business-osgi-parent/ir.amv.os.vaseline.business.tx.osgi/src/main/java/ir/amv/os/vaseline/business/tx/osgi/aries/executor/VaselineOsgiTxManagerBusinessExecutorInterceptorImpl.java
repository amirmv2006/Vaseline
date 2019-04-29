package ir.amv.os.vaseline.business.tx.osgi.aries.executor;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxy.IProxyInterceptor;
import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import ir.amv.os.vaseline.business.tx.osgi.aries.executor.txblueprint.TransactionAttribute;
import ir.amv.os.vaseline.business.tx.osgi.aries.executor.txblueprint.TransactionToken;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.Coordinator;

import javax.transaction.RollbackException;
import javax.transaction.Transaction;
import javax.transaction.TransactionManager;
import javax.transaction.Transactional;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IProxyInterceptor.class
)
public class VaselineOsgiTxManagerBusinessExecutorInterceptorImpl
        implements IProxyInterceptor<TransactionToken> {

    private TransactionManager tm;
    private Coordinator coordinator;

    @Override
    public int priority() {
        return Integer.MAX_VALUE - 100;
    }

    @Override
    public boolean appliesTo(final MethodExecution methodExecution) {
        Transactional buinessMetadata = ReflectionUtil.getMethodAnnotationInHierarchy(
                Transactional.class,
                methodExecution.getOriginalObject().getClass(),
                methodExecution.getMethod().getName(),
                methodExecution.getMethod().getParameterTypes());
        return buinessMetadata != null;
    }

    @Override
    public TransactionToken preExecute(final MethodExecution methodExecution) throws BaseVaselineServerException {
        try {
            Transactional txAnnot = getTxAnnotationFromBusinessAction(methodExecution);
            TransactionAttribute txAttr = TransactionAttribute.fromValue(txAnnot.value());
            TransactionToken token = txAttr.begin(tm);
            String coordName = "txInterceptor." + methodExecution.getMethod().getName();
            Coordination coord = coordinator.begin(coordName, 0);
            token.setCoordination(coord);
            return token;
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not start transaction", e);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    private <R> Transactional getTxAnnotationFromBusinessAction(final MethodExecution methodExecution) {
        Transactional transactional = ReflectionUtil.getMethodAnnotationInHierarchy(Transactional.class,
                methodExecution.getOriginalObject().getClass(), methodExecution.getMethod().getName(), methodExecution
                        .getMethod().getParameterTypes());
        if (transactional == null) {
            try {
                transactional = VaselineOsgiTxManagerBusinessExecutorInterceptorImpl.class
                        .getDeclaredMethod("getTxAnnotationFromBusinessAction", MethodExecution.class)
                        .getAnnotation(Transactional.class);
            } catch (NoSuchMethodException ignored) {
            }
        }
        return transactional;
    }

    @Override
    public <R> void postExecuteSuccessfully(
            final MethodExecution methodExecution,
            final R returnedValue,
            final TransactionToken preExecResult) throws BaseVaselineServerException {
        try {
            LOGGER.log(VaselineLogLevel.DEBUG, "PostCallWithReturn for bean {}, method {}.", methodExecution
                    .getOriginalObject(), methodExecution.getMethod());
            // it is possible transaction is not involved at all
            if (preExecResult == null) {
                return;
            }
            safeEndCoordination(preExecResult);
            try {
                preExecResult.getTransactionAttribute().finish(tm, preExecResult);
            } catch (Exception e) {
                // We are throwing an exception, so we don't error it out
                RollbackException rbe = new javax.transaction.RollbackException();
                rbe.addSuppressed(e);
                throw rbe;
            }
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not commit", e);
        }
    }

    @Override
    public void postExecuteException(
            final MethodExecution methodExecution,
            final Throwable t,
            final TransactionToken preExecResult) throws BaseVaselineServerException {
        try {
            LOGGER.log(VaselineLogLevel.DEBUG, "PostCallWithException for bean {}, method {}.", methodExecution
                    .getOriginalObject(), methodExecution.getMethod().getName(), t);
            safeEndCoordination(preExecResult);
            Transaction tran = preExecResult.getActiveTransaction();
            if (tran != null && isRollBackException(t, methodExecution)) {
                tran.setRollbackOnly();
                LOGGER.log(VaselineLogLevel.DEBUG, "Setting transaction to rollback only because of exception ", t);
            }
            preExecResult.getTransactionAttribute().finish(tm, preExecResult);
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not rollback", e);
        }
    }

    private void safeEndCoordination(final TransactionToken token) {
        try {
            if (token != null && token.getCoordination() != null) {
                token.getCoordination().end();
            }
        } catch (Exception e) {
            LOGGER.log(VaselineLogLevel.DEBUG, e.getMessage(), e);
        }
    }

    private boolean isRollBackException(Throwable ex, final MethodExecution methodExecution) {
        if (methodExecution != null) {
            Transactional txAnnot = getTxAnnotationFromBusinessAction(methodExecution);
            if (txAnnot == null) {
                return isUncheckedException(ex);
            } else {
                //check dontRollbackOn first, since according to spec it has precedence
                for (Class dontRollbackClass : txAnnot.dontRollbackOn()) {
                    if (dontRollbackClass.isInstance(ex)) {
                        LOGGER.log(VaselineLogLevel.DEBUG, "Current exception {} found in element dontRollbackOn.",
                                ex.getClass());
                        return false;
                    }
                }
                //don't need to check further elements if ex is an unchecked exception
                if (isUncheckedException(ex)) {
                    return true;
                }
                for (Class rollbackExceptionClass : txAnnot.rollbackOn()) {
                    if (rollbackExceptionClass.isInstance(ex)) {
                        LOGGER.log(VaselineLogLevel.DEBUG, "Current exception {} found in element rollbackOn.", ex
                                .getClass());
                        return true;
                    }
                }
            }
        } else {
            return isUncheckedException(ex);
        }
        return false;
    }

    public static boolean isUncheckedException(Throwable ex) {
        return ex instanceof RuntimeException || ex instanceof Error;
    }

    @Reference
    public void setTm(final TransactionManager tm) {
        this.tm = tm;
    }

    @Reference
    public void setCoordinator(final Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
