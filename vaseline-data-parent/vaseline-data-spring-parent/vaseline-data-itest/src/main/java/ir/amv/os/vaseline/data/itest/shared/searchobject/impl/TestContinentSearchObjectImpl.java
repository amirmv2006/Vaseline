package ir.amv.os.vaseline.data.itest.shared.searchobject.impl;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.itest.shared.searchobject.ITestContinentSearchObject;

public class TestContinentSearchObjectImpl extends BaseSearchObjectImpl
		implements
			ITestContinentSearchObject {

	private IBasePropertyCondition<?, String> continentName;

	public IBasePropertyCondition<?, String> getContinentName() {
		return continentName;
	}

	public void setContinentName(IBasePropertyCondition<?, String> continentName) {
		this.continentName = continentName;
	}
}