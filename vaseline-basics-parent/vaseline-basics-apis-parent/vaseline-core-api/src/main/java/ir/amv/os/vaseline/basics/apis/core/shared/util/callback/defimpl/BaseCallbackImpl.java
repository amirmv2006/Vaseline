package ir.amv.os.vaseline.basics.apis.core.shared.util.callback.defimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.util.callback.IBaseCallback;

public abstract class BaseCallbackImpl<T, F> implements IBaseCallback<T, F> {

	@Override
	public void onFailure(F failure) throws Exception {
	}

}
