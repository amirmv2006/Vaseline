package ir.amv.os.vaseline.base.core.shared.util.callback.impl;

import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;

public abstract class BaseCallbackImpl<T, F> implements IBaseCallback<T, F> {

	@Override
	public void onFailure(F failure) {
	}

}
