package ir.amv.os.vaseline.base.core.api.shared.base.dto.sort;

import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.baseimpl.BaseEmptyDtoImpl;

public class SortDto extends BaseEmptyDtoImpl {

	private static final long serialVersionUID = 1L;

	private String propertyName;
	private Boolean ascending;

	public SortDto() {
	}

	public SortDto(String propertyName, Boolean ascending) {
		super();
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Boolean getAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}
}
