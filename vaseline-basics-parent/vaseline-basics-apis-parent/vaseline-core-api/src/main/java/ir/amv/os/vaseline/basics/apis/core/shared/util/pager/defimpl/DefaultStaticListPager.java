package ir.amv.os.vaseline.basics.apis.core.shared.util.pager.defimpl;

import ir.amv.os.vaseline.basics.apis.core.shared.util.pager.IBaseListPager;

import java.util.List;

public class DefaultStaticListPager<D> implements IBaseListPager<D> {
	
	private final List<D> list;
	private int pageSize;
	private int pageNo;
	private List<D> currentPage;

	public DefaultStaticListPager(List<D> list, int pageSize) {
		this.list = list;
		this.pageSize = pageSize;
	}

	@Override
	public int getTotalCount() {
		return list.size();
	}

	@Override
	public void setTotalCount(int totalCount) {
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public int getCurrentPageNo() {
		return pageNo;
	}

	@Override
	public void setCurrentPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public List<D> getCurrentPage() {
		return currentPage;
	}

	@Override
	public void reset() {
		pageNo = -1;
//		refreshCurrentPage();
	}

	@Override
	public boolean nextPage() {
		pageNo++;
		if ((pageNo * pageSize) < list.size()) {
			refreshCurrentPage();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean previousPage() {
		pageNo--;
		if (pageNo >= 0) {
			refreshCurrentPage();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void refreshCurrentPage() {
		int startIndex = pageNo * pageSize;
		int endIndex = (pageNo + 1) * pageSize;
		if (endIndex > list.size()) {
			endIndex = list.size();
		}
		currentPage = list.subList(startIndex, endIndex);
	}

	@Override
	public boolean isInitialized() {
		return true;
	}
}
