package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionZero;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Amir
 */
public class BusinessFunctionZeroImpl<R>
        extends AbstractBusinessActionImpl<R> {

    private final IBusinessFunctionZero<R> businessFunction;

    public BusinessFunctionZeroImpl(
            final Class<?> runningClass,
            final Method declaredMethod,
            final IBusinessFunctionZero<R> businessFunction, final IBusinessMetadata... businessMetadata) {
        super(UUID.randomUUID().toString(), runningClass, declaredMethod, new Object[]{}, businessMetadata);
        this.businessFunction = businessFunction;
    }

    @Override
    public R execute() throws BaseVaselineServerException {
        return businessFunction.apply();
    }
}
