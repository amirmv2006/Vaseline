package ir.amv.os.vaseline.data.test.model.shared.searchobject.impl;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;
import java.util.Date;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestContinentSearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestStateSearchObject;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

public class TestCountrySearchObjectImpl extends BaseSearchObjectImpl
		implements
			ITestCountrySearchObject {

	private IBasePropertyCondition<?, String> countryName;
	private IBasePropertyCondition<?, Long> population;
	private IBasePropertyCondition<?, Boolean> areRacist;
	private IBasePropertyCondition<?, Date> birthDate;
	private ITestContinentSearchObject continent;
	private ITestStateSearchObject states;

	public IBasePropertyCondition<?, String> getCountryName() {
		return countryName;
	}

	public void setCountryName(IBasePropertyCondition<?, String> countryName) {
		this.countryName = countryName;
	}

	public IBasePropertyCondition<?, Long> getPopulation() {
		return population;
	}

	public void setPopulation(IBasePropertyCondition<?, Long> population) {
		this.population = population;
	}

	public IBasePropertyCondition<?, Boolean> getAreRacist() {
		return areRacist;
	}

	public void setAreRacist(IBasePropertyCondition<?, Boolean> areRacist) {
		this.areRacist = areRacist;
	}

	public IBasePropertyCondition<?, Date> getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(IBasePropertyCondition<?, Date> birthDate) {
		this.birthDate = birthDate;
	}

	public ITestContinentSearchObject getContinent() {
		return continent;
	}

	public void setContinent(ITestContinentSearchObject continent) {
		this.continent = continent;
	}

	public ITestStateSearchObject getStates() {
		return states;
	}

	public void setStates(ITestStateSearchObject states) {
		this.states = states;
	}
}