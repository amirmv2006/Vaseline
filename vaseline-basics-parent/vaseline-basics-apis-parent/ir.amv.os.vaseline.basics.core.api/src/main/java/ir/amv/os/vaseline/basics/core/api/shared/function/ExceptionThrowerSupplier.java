package ir.amv.os.vaseline.basics.core.api.shared.function;

@FunctionalInterface
public interface ExceptionThrowerSupplier<R> {
    R get() throws Exception;
}
