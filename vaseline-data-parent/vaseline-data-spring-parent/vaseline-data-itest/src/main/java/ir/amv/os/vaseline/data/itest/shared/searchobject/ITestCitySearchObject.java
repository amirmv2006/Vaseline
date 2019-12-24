package ir.amv.os.vaseline.data.itest.shared.searchobject;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

public interface ITestCitySearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getCityName();

	void setCityName(IBasePropertyCondition<?, String> cityName);
}