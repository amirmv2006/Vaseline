package ir.amv.os.vaseline.business.basic.api.server.action.executor;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.IBusinessAction;

/**
 * @author Amir
 */
public interface IVaselineBusinessActionExecutor {

    <R> R executeAction(IBusinessAction<R> action) throws BaseVaselineServerException;
}
