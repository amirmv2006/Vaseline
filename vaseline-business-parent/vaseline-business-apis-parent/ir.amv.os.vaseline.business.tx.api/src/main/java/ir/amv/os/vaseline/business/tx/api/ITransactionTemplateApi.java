package ir.amv.os.vaseline.business.tx.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;

import java.util.function.Supplier;

/**
 * @author Amir
 */
public interface ITransactionTemplateApi {

    <R> R doInATransaction(final String actionName, Supplier<R> transactionalAction) throws BaseBusinessException;
}
