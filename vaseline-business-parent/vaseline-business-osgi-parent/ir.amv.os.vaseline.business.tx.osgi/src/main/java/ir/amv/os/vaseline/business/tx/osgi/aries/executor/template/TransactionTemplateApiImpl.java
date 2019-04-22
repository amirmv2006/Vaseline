package ir.amv.os.vaseline.business.tx.osgi.aries.executor.template;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.logging.api.server.logger.VaselineLogLevel;
import ir.amv.os.vaseline.basics.logging.common.osgi.server.helper.LOGGER;
import ir.amv.os.vaseline.business.tx.api.ITransactionTemplateApi;
import ir.amv.os.vaseline.business.tx.osgi.aries.executor.VaselineOsgiTxManagerBusinessExecutorInterceptorImpl;
import ir.amv.os.vaseline.business.tx.osgi.aries.executor.txblueprint.TransactionAttribute;
import ir.amv.os.vaseline.business.tx.osgi.aries.executor.txblueprint.TransactionToken;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.Coordinator;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.util.function.Supplier;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = ITransactionTemplateApi.class
)
public class TransactionTemplateApiImpl
        implements ITransactionTemplateApi {

    private TransactionManager tm;
    private Coordinator coordinator;

    @Override
    public <R> R doInATransaction(final String actionName, final Supplier<R> transactionalAction) throws BaseVaselineServerException {
        TransactionToken token = null;
        TransactionAttribute txAttr = TransactionAttribute.REQUIRED;
        try {
            token = txAttr.begin(tm);
            String coordName = "txTemplate." + actionName;
            Coordination coord = coordinator.begin(coordName, 0);
            token.setCoordination(coord);
            return transactionalAction.get();
        } catch (Exception e) {
            if (token != null && token.getActiveTransaction() != null &&
                    VaselineOsgiTxManagerBusinessExecutorInterceptorImpl.isUncheckedException(e)) {
                try {
                    LOGGER.log(VaselineLogLevel.DEBUG, "Setting transaction to rollback only because of exception ", e);
                    token.getActiveTransaction().setRollbackOnly();
                } catch (SystemException e1) {
                    throw new BaseVaselineServerException("Can rollback tx", e);
                }
            }
            throw new BaseVaselineServerException("exception while doing " + actionName, e);
        } finally {
            safeEndCoordination(token);
            finish(token, txAttr);
        }
    }

    private void finish(final TransactionToken token, final TransactionAttribute txAttr) throws
            BaseVaselineServerException {
        try {
            txAttr.finish(tm, token);
        } catch (Exception e) {
            throw new BaseVaselineServerException("Can not finish tx");
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

    @Reference
    public void setTm(final TransactionManager tm) {
        this.tm = tm;
    }

    @Reference
    public void setCoordinator(final Coordinator coordinator) {
        this.coordinator = coordinator;
    }
}
