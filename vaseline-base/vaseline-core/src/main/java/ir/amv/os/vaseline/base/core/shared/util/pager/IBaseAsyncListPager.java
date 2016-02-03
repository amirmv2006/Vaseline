package ir.amv.os.vaseline.base.core.shared.util.pager;

import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;

import java.util.List;

public interface IBaseAsyncListPager<D> extends IBaseListPager<D> {

	void setLoadDataCallback(IBaseCallback<IBaseCallback<List<D>, Void>, Void> loadDataCallback);
	void setCountDataCallback(IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback);
}
