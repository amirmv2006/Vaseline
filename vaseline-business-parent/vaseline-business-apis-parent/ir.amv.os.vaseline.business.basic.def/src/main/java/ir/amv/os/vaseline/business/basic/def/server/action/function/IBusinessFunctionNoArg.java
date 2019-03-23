package ir.amv.os.vaseline.business.basic.def.server.action.function;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.basic.api.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.basic.def.server.action.IDefaultBusinessAction;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IBusinessFunctionNoArg<R> extends IDefaultBusinessAction<R> {

    R apply() throws BaseVaselineServerException;

    @Override
    default R execute(final IVaselineBusinessActionExecutor businessActionExecutor) throws
            BaseVaselineServerException {
        return apply();
    }
}
