package ir.amv.os.vaseline.data.test.model.shared.searchobject;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

public interface ITestContinentSearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getContinentName();

	void setContinentName(IBasePropertyCondition<?, String> continentName);
}