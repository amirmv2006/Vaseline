package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionThree;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionTwo;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Amir
 */
public class BusinessFunctionThreeImpl<P1, P2, P3, R>
        extends AbstractBusinessActionImpl<R> {

    private final IBusinessFunctionThree<P1, P2, P3, R> businessFunction;

    public BusinessFunctionThreeImpl(
            final Class<?> runningClass,
            final Method declaredMethod,
            final P1 p1,
            final P2 p2,
            final P3 p3,
            final IBusinessFunctionThree<P1, P2, P3, R> businessFunction, final IBusinessMetadata... businessMetadata) {
        super(UUID.randomUUID().toString(), runningClass, declaredMethod, new Object[]{p1, p2, p3}, businessMetadata);
        this.businessFunction = businessFunction;
    }

    @Override
    public R execute() throws BaseVaselineServerException {
        return businessFunction.apply((P1) getActionParams()[0], (P2) getActionParams()[1], (P3) getActionParams()[2]);
    }
}
