package ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback;

public interface IBaseCallback<T, F> {

	void onSuccess(T result);
	void onFailure(F failure);
}