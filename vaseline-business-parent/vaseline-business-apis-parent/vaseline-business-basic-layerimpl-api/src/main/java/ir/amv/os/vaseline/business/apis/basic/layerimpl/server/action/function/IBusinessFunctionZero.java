package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IBusinessFunctionZero<R> {

    R apply() throws BaseVaselineServerException;

}
