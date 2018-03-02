package ir.amv.os.vaseline.data.test.model.shared.searchobject.impl;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCitySearchObject;

public class TestCitySearchObjectImpl extends BaseSearchObjectImpl
		implements
			ITestCitySearchObject {

	private IBasePropertyCondition<?, String> cityName;

	public IBasePropertyCondition<?, String> getCityName() {
		return cityName;
	}

	public void setCityName(IBasePropertyCondition<?, String> cityName) {
		this.cityName = cityName;
	}
}