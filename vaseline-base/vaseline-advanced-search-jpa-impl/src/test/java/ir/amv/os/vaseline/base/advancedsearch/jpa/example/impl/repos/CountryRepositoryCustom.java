package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCountry;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCountrySearchObject;

import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public interface CountryRepositoryCustom {

    List<TestCountry> search(ITestCountrySearchObject countrySearchObject);
}
