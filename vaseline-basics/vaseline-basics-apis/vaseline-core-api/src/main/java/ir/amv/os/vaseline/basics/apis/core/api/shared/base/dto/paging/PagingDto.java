package ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.paging;

import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base.baseimpl.BaseEmptyDtoImpl;
import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.sort.SortDto;

import java.util.List;

public class PagingDto extends BaseEmptyDtoImpl {

	private static final long serialVersionUID = 1L;

	private List<SortDto> sortList;
	private Integer pageNumber;
	private Integer pageSize;

	public PagingDto() {
		// TODO Auto-generated constructor stub
	}

	public PagingDto(List<SortDto> sortList, Integer pageNumber,
			Integer pageSize) {
		super();
		this.sortList = sortList;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public List<SortDto> getSortList() {
		return sortList;
	}

	public void setSortList(List<SortDto> sortList) {
		this.sortList = sortList;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public static PagingDto maxPage() {
		return new PagingDto(null, 0, Integer.MAX_VALUE);
	}
}
