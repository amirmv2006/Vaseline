package ir.amv.os.vaseline.base.core.shared.util.callback;

public interface IBaseCallback<T, F> {

	void onSuccess(T result);
	void onFailure(F failure);
}
