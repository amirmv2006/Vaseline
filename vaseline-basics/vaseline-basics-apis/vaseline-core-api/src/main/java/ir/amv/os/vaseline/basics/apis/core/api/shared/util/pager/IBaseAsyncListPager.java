package ir.amv.os.vaseline.basics.apis.core.api.shared.util.pager;

import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.basics.apis.core.api.shared.util.callback.IBaseDoubleParameterCallback;

import java.util.List;

public interface IBaseAsyncListPager<D> extends IBaseListPager<D> {

	void setLoadDataCallback(IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> loadDataCallback);
	IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> getLoadDataCallback();
	void setCountDataCallback(IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback);
	IBaseCallback<IBaseCallback<Integer, Void>, Void> getCountDataCallback();
}
