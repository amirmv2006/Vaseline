package ir.amv.os.vaseline.data.test.model.shared.searchobject;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import java.util.Date;

public interface ITestCountrySearchObject extends IBaseSearchObject {

	IBasePropertyCondition<?, String> getCountryName();

	void setCountryName(IBasePropertyCondition<?, String> countryName);

	IBasePropertyCondition<?, Long> getPopulation();

	void setPopulation(IBasePropertyCondition<?, Long> population);

	IBasePropertyCondition<?, Boolean> getAreRacist();

	void setAreRacist(IBasePropertyCondition<?, Boolean> areRacist);

	IBasePropertyCondition<?, Date> getBirthDate();

	void setBirthDate(IBasePropertyCondition<?, Date> birthDate);

	ITestContinentSearchObject getContinent();

	void setContinent(ITestContinentSearchObject continent);

	ITestStateSearchObject getStates();

	void setStates(ITestStateSearchObject states);
}