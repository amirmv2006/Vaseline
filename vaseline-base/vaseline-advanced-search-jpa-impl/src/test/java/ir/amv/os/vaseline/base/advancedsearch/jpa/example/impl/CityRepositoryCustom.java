package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public interface CityRepositoryCustom {

    List<City> search(ICitySearchObject citySearchObject);
}
