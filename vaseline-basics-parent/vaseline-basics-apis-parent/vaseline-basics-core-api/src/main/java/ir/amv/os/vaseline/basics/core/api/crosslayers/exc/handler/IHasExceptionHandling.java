package ir.amv.os.vaseline.basics.core.api.crosslayers.exc.handler;

import ir.amv.os.vaseline.basics.core.api.utils.fn.ExceptionalSupplier;

public interface IHasExceptionHandling<E extends Exception> {

    <R> R handleExceptions(ExceptionalSupplier<R, E> supplier) throws Exception;
}
