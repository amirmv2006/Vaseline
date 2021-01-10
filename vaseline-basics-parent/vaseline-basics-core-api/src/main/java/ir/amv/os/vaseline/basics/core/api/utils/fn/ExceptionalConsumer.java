package ir.amv.os.vaseline.basics.core.api.utils.fn;

import lombok.SneakyThrows;

import java.util.function.Consumer;

@FunctionalInterface
public interface ExceptionalConsumer<T, E extends Exception> {

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(T t) throws E;

    static <T, E extends Exception> Consumer<T> sneakyConsumer(ExceptionalConsumer<T, E> consumer) {
        return t -> consumeSneakily(t, consumer);
    }

    @SneakyThrows
    static <T> void consumeSneakily(T val, ExceptionalConsumer<T, ?> consumer) {
        consumer.accept(val);
    }
}
