package ir.amv.os.vaseline.basics.core.api.utils.fn.optional;

import java.util.Optional;
import java.util.function.Supplier;

public final class OptionalUtils {

    private OptionalUtils() {
    }

    public static <O, E extends Exception> O getOrElseThrow(Optional<O> optional, Supplier<E> excSupplier) throws E {
        O o = optional.orElse(null);
        if (o == null) {
            throw excSupplier.get();
        }
        return o;
    }
}
