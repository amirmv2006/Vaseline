package ir.amv.os.vaseline.basics.core.api.utils.fn;

@FunctionalInterface
public interface ExceptionalFunction<T, R, E extends Exception> {

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t) throws E;
}
