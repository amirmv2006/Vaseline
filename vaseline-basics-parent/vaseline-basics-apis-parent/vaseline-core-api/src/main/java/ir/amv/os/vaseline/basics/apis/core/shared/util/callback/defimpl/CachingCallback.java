package ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;

/**
 * Created by AMV on 2/29/2016.
 */
public abstract class CachingCallback<T>
        extends BaseCallbackImpl<IBaseCallback<T, Void>, Void>{

    private T value;

    @Override
    public void onSuccess(IBaseCallback<T, Void> result) throws Exception {
        result.onSuccess(getValue());
    }

    private T getValue() {
        if (value == null) {
            value = fetchValue();
        }
        return value;
    }

    public abstract T fetchValue();
}