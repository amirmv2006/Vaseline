package ir.amv.os.vaseline.base.core.api.shared.util.callback;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseDoubleParameterCallback<T1, T2, F> {

    void onSuccess(T1 firstParam, T2 secondParameter);
    void onFailure(F failure);
}
