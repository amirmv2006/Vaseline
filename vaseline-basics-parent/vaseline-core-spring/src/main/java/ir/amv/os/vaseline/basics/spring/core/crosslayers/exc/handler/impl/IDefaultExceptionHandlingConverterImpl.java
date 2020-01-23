package ir.amv.os.vaseline.basics.spring.core.crosslayers.exc.handler.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.exc.handler.IHasExceptionHandling;
import ir.amv.os.vaseline.basics.core.api.utils.fn.ExceptionalSupplier;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IDefaultConverterSpringApi;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;

public interface IDefaultExceptionHandlingConverterImpl<E1 extends Exception, E2 extends Exception>
        extends IHasExceptionHandling<E1>, IDefaultConverterSpringApi {

    default Class<E1> getE1Class() {
        return GenericUtils.getGeneric(getClass(), IDefaultExceptionHandlingConverterImpl.class, 0);
    }
    default Class<E2> getE2Class() {
        return GenericUtils.getGeneric(getClass(), IDefaultExceptionHandlingConverterImpl.class, 1);
    }

    default E2 convertCaughtException(E1 e1) {
        return getConversionService().convert(e1, getE2Class());
    }

    default E2 convertUnCaughtException(Exception e) {
        return getConversionService().convert(e, getE2Class());
    }

    @Override
    default <R> R handleExceptions(ExceptionalSupplier<R, E1> supplier) throws E2 {
        try {
            return supplier.get();
        } catch (Exception e) {
            try {
                E1 e1  = caughtExceptionToE1(e);
                throw convertCaughtException(e1);
            } catch (Exception ex) {
                throw convertUnCaughtException(ex);
            }
        }
    }

    default E1 caughtExceptionToE1(Exception e1) throws Exception {
        if (getE1Class().isInstance(e1)) {
            return getE1Class().cast(e1);
        }
        throw e1;
    }
}
