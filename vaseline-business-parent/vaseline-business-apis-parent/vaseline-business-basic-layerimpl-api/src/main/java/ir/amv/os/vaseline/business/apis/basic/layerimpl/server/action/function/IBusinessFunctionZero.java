package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.IImplemtedBusinessAction;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IBusinessFunctionZero<R> extends IImplemtedBusinessAction<R> {

    R apply() throws BaseVaselineServerException;

    @Override
    default R execute(final IVaselineBusinessActionExecutor businessActionExecutor) throws
            BaseVaselineServerException {
        return apply();
    }
}
