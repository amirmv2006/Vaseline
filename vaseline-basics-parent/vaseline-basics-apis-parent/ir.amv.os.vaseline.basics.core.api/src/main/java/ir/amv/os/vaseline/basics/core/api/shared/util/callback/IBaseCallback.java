package ir.amv.os.vaseline.basics.core.api.shared.util.callback;

@FunctionalInterface
public interface IBaseCallback<T, F> {

	void onSuccess(T result) throws Exception;
	default void onFailure(F failure) throws Exception {
		// do nothing
	}
}
