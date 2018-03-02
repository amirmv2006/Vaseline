package ir.amv.os.vaseline.data.test.model.shared.searchobject.impl;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCitySearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestStateSearchObject;

public class TestStateSearchObjectImpl extends BaseSearchObjectImpl
		implements
			ITestStateSearchObject {

	private IBasePropertyCondition<?, String> stateName;
	private ITestCitySearchObject cities;

	public IBasePropertyCondition<?, String> getStateName() {
		return stateName;
	}

	public void setStateName(IBasePropertyCondition<?, String> stateName) {
		this.stateName = stateName;
	}

	public ITestCitySearchObject getCities() {
		return cities;
	}

	public void setCities(ITestCitySearchObject cities) {
		this.cities = cities;
	}
}