package ir.amv.os.vaseline.data.test.model.shared.searchobject;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

public interface ITestStateSearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getStateName();

	void setStateName(IBasePropertyCondition<?, String> stateName);

	ITestCitySearchObject getCities();

	void setCities(ITestCitySearchObject cities);
}