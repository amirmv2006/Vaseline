package ir.amv.os.vaseline.basics.core.api.utils.fn;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws E;
}
