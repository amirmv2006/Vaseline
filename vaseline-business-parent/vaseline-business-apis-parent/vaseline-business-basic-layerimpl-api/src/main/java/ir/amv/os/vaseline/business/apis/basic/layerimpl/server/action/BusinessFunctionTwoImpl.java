package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionTwo;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Amir
 */
public class BusinessFunctionTwoImpl<P1, P2, R>
        extends AbstractBusinessActionImpl<R> {

    private final IBusinessFunctionTwo<P1, P2, R> businessFunction;

    public BusinessFunctionTwoImpl(
            final Class<?> runningClass,
            final Method declaredMethod,
            final P1 p1,
            final P2 p2,
            final IBusinessFunctionTwo<P1, P2, R> businessFunction, final IBusinessMetadata... businessMetadata) {
        super(UUID.randomUUID().toString(), runningClass, declaredMethod, new Object[]{p1, p2}, businessMetadata);
        this.businessFunction = businessFunction;
    }

    @Override
    public R execute() throws BaseVaselineServerException {
        return businessFunction.apply((P1) getActionParams()[0], (P2) getActionParams()[1]);
    }
}
