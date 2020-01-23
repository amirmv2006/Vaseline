package ir.amv.os.vaseline.basics.core.api.utils.fn;

@FunctionalInterface
public interface ExceptionalConsumer<T, E extends Exception> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws E;
}
