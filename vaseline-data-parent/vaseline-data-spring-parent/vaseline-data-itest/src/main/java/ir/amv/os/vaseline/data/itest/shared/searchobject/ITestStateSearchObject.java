package ir.amv.os.vaseline.data.itest.shared.searchobject;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

public interface ITestStateSearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getStateName();

	void setStateName(IBasePropertyCondition<?, String> stateName);

	ITestCitySearchObject getCities();

	void setCities(ITestCitySearchObject cities);
}