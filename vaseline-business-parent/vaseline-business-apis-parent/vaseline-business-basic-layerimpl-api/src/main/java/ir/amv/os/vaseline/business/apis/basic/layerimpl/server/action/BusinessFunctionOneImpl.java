package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionOne;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Amir
 */
public class BusinessFunctionOneImpl<P, R>
        extends AbstractBusinessActionImpl<R> {

    private final IBusinessFunctionOne<P, R> businessFunction;

    public BusinessFunctionOneImpl(
            final Class<?> runningClass,
            final Method declaredMethod,
            final P actionParam,
            final IBusinessFunctionOne<P, R> businessFunction, final IBusinessMetadata... businessMetadata) {
        super(UUID.randomUUID().toString(), runningClass, declaredMethod, new Object[]{actionParam}, businessMetadata);
        this.businessFunction = businessFunction;
    }

    @Override
    public R execute() throws BaseVaselineServerException {
        return businessFunction.apply((P) getActionParams()[0]);
    }
}
