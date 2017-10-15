package ir.amv.os.vaseline.basics.apis.core.shared.util.callback;

public interface IBaseCallback<T, F> {

	void onSuccess(T result) throws Exception;
	void onFailure(F failure) throws Exception;
}
