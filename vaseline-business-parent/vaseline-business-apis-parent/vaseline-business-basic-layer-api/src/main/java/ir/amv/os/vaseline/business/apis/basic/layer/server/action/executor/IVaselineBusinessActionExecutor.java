package ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;

/**
 * @author Amir
 */
public interface IVaselineBusinessActionExecutor {

    <R> R executeAction(IBusinessAction<R> action) throws BaseVaselineServerException;
}
