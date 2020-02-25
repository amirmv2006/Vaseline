package ir.amv.os.vaseline.basics.core.api.utils.fn;

import java.util.function.Function;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t) throws E;

    static <T, R, E extends Exception> Function<T, R> sneakyFunction(ExceptionalFunction<T, R, E> fn) {
        return t -> ExceptionalSupplier.supplySneakily(() -> fn.apply(t));
    }
}
