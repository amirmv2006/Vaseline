package ir.amv.os.vaseline.basics.apis.core.api.shared.util.pager;

import java.util.List;

public interface IBaseListPager<D> {
	
	int getTotalCount();
	void setTotalCount(int totalCount);
	
	int getPageSize();
	void setPageSize(int pageSize);

	int getCurrentPageNo();
	void setCurrentPageNo(int pageNo);
	
	List<D> getCurrentPage();
	
	void reset();
	boolean nextPage();
	boolean previousPage();
	void refreshCurrentPage();
	
	boolean isInitialized();
}
