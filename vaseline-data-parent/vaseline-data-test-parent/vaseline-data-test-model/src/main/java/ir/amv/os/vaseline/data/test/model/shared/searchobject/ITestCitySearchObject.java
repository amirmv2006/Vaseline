package ir.amv.os.vaseline.data.test.model.shared.searchobject;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

public interface ITestCitySearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getCityName();

	void setCityName(IBasePropertyCondition<?, String> cityName);
}