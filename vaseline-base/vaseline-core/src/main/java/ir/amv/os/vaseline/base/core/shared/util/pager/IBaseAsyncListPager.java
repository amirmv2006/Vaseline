package ir.amv.os.vaseline.base.core.shared.util.pager;

import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;
import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseDoubleParameterCallback;

import java.util.List;

public interface IBaseAsyncListPager<D> extends IBaseListPager<D> {

	void setLoadDataCallback(IBaseDoubleParameterCallback<IBaseCallback<List<D>, Void>, PagingDto, Void> loadDataCallback);
	void setCountDataCallback(IBaseCallback<IBaseCallback<Integer, Void>, Void> countDataCallback);
}
