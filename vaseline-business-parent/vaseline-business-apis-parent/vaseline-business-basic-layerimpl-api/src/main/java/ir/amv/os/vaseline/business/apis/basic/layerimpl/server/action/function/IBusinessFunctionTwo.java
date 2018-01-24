package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IBusinessFunctionTwo<P1, P2, R> {

    R apply(P1 p1, P2 p2) throws BaseVaselineServerException;

}
