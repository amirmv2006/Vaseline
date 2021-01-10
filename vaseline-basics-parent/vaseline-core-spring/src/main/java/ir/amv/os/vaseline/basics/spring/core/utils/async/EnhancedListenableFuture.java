package ir.amv.os.vaseline.basics.spring.core.utils.async;

import ir.amv.os.vaseline.basics.core.api.utils.fn.ExceptionalFunction;
import org.springframework.util.concurrent.CompletableToListenableFutureAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureAdapter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EnhancedListenableFuture<R>
        extends ListenableFutureAdapter<R, R> {
    /**
     * Construct a new {@code ListenableFutureAdapter} with the given adaptee.
     *
     * @param adaptee the future to adapt to
     */
    private EnhancedListenableFuture(final ListenableFuture<R> adaptee) {
        super(adaptee);
    }

    @Override
    protected R adapt(final R adapteeResult) throws ExecutionException {
        return adapteeResult;
    }

    public <S, E extends Exception> EnhancedListenableFuture<S> map(
            ExceptionalFunction<R, S, E> converterFn) {
        return new EnhancedListenableFuture<>(new ListenableFutureAdapter<S, R>(this) {
            @Override
            protected S adapt(final R adapteeResult) throws ExecutionException {
                try {
                    return converterFn.apply(adapteeResult);
                } catch (Exception e) {
                    throw new ExecutionException(e);
                }
            }
        });
    }

    public <S, E extends Exception> EnhancedListenableFuture<S> flatMap(
            ExceptionalFunction<R, ListenableFuture<S>, E> converterFn) {
        CompletableFuture<S> cf = new CompletableFuture<>();
        this.addCallback(
                r -> {
                    try {
                        converterFn.apply(r).addCallback(
                                cf::complete,
                                cf::completeExceptionally
                        );
                    } catch (Exception e) {
                        cf.completeExceptionally(e);
                    }
                },
                cf::completeExceptionally
        );
        return new EnhancedListenableFuture<>(new CompletableToListenableFutureAdapter<>(cf));
    }

    public static <T> EnhancedListenableFuture<T> of(ListenableFuture<T> listenableFuture) {
        return new EnhancedListenableFuture<>(listenableFuture);
    }
}
