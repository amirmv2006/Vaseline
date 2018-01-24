package ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;

import java.util.Objects;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IBusinessFunctionOne<P, R> {

    R apply(P t) throws BaseVaselineServerException;

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(IBusinessFunctionOne)
     */
    default <V> IBusinessFunctionOne<V, R> compose(IBusinessFunctionOne<? super V, ? extends P> before) throws BaseVaselineServerException {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose(IBusinessFunctionOne)
     */
    default <V> IBusinessFunctionOne<P, V> andThen(IBusinessFunctionOne<? super R, ? extends V> after) throws BaseVaselineServerException {
        Objects.requireNonNull(after);
        return (P t) -> after.apply(apply(t));
    }
}
