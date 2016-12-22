package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestCity;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestCitySearchObject;

import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public interface CityRepositoryCustom {

    List<TestCity> search(ITestCitySearchObject citySearchObject);
}
