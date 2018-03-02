package ir.amv.os.vaseline.business.osgi.tx.aries.executor;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.logging.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.osgi.logging.common.server.helper.LOGGER;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessExecutorInterceptor;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineAllBuinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.osgi.tx.aries.executor.txblueprint.TransactionAttribute;
import ir.amv.os.vaseline.business.osgi.tx.aries.executor.txblueprint.TransactionToken;
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
        service = IVaselineBusinessExecutorInterceptor.class
)
public class VaselineOsgiTxManagerBusinessExecutorInterceptorImpl
        implements IVaselineBusinessExecutorInterceptor<TransactionToken> {

    private TransactionManager tm;
    private Coordinator coordinator;

    @Override
    public <R> boolean appliesTo(final IBusinessAction<R> businessAction) {
        IBusinessMetadata[] businessMetadata = businessAction.getBusinessMetadata();
        if (businessMetadata != null) {
            for (IBusinessMetadata businessMetadatum : businessMetadata) {
                if (businessMetadatum.equals(VaselineAllBuinessMetadata.VASELINE_DB_READ_ONLY) ||
                        businessMetadatum.equals(VaselineAllBuinessMetadata.VASELINE_DB_READ_WRITE)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <R> TransactionToken preExecute(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
        try {
            Transactional txAnnot = getTxAnnotationFromBusinessAction(businessAction);
            TransactionAttribute txAttr = TransactionAttribute.fromValue(txAnnot.value());
            TransactionToken token = txAttr.begin(tm);
            String coordName = "txInterceptor." + businessAction.getActionName();
            Coordination coord = coordinator.begin(coordName, 0);
            token.setCoordination(coord);
            return token;
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not start transaction", e);
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    private <R> Transactional getTxAnnotationFromBusinessAction(final IBusinessAction<R> businessAction) {
        Transactional transactional = ReflectionUtil.getMethodAnnotationInHierarchy(Transactional.class,
                businessAction.getRunningClass(), businessAction.getDeclaredMethod().getName(), businessAction
                        .getDeclaredMethod().getParameterTypes());
        if (transactional == null) {
            try {
                transactional = VaselineOsgiTxManagerBusinessExecutorInterceptorImpl.class
                        .getDeclaredMethod("getTxAnnotationFromBusinessAction", IBusinessAction.class)
                        .getAnnotation(Transactional.class);
            } catch (NoSuchMethodException ignored) {
            }
        }
        return transactional;
    }

    @Override
    public <R> void postExecuteSuccessfully(
            final IBusinessAction<R> businessAction,
            final R returnedValue,
            final TransactionToken preExecResult) throws BaseVaselineServerException {
        try {
            LOGGER.log(VaselineLogLevel.DEBUG, "PostCallWithReturn for bean {}, method {}.", businessAction
                    .getActionName(), businessAction.getDeclaredMethod());
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
    public <R> void postExecuteException(
            final IBusinessAction<R> businessAction,
            final Throwable t,
            final TransactionToken preExecResult) throws BaseVaselineServerException {
        try {
            LOGGER.log(VaselineLogLevel.DEBUG, "PostCallWithException for bean {}, method {}.", businessAction
                    .getActionName(), businessAction.getDeclaredMethod().getName(), t);
            safeEndCoordination(preExecResult);
            Transaction tran = preExecResult.getActiveTransaction();
            if (tran != null && isRollBackException(t, businessAction)) {
                tran.setRollbackOnly();
                LOGGER.log(VaselineLogLevel.DEBUG, "Setting transaction to rollback only because of exception ", t);
            }
            preExecResult.getTransactionAttribute().finish(tm, preExecResult);
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not rollback", e);
        }
    }

    @Override
    public Class<TransactionToken> tokenClass() {
        return TransactionToken.class;
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

    private boolean isRollBackException(Throwable ex, final IBusinessAction<?> businessAction) {
        if (businessAction != null) {
            Transactional txAnnot = getTxAnnotationFromBusinessAction(businessAction);
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

    private static boolean isUncheckedException(Throwable ex) {
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
