package ir.amv.os.vaseline.basics.core.api.utils.fn;

import lombok.SneakyThrows;

import java.util.function.Supplier;

@FunctionalInterface
public interface ExceptionalSupplier<T, E extends Exception> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws E;

    static <R, E extends Exception> Supplier<R> sneakySupplier(ExceptionalSupplier<R, E> s) {
        return () -> supplySneakily(s::get);
    }

    @SneakyThrows
    static <R> R supplySneakily(ExceptionalSupplier<R, ?> s) {
        return s.get();
    }
}
