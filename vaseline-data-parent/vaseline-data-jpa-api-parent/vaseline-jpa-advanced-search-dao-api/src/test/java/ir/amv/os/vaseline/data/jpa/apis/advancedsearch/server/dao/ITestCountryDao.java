package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

public interface ITestCountryDao
        extends IBaseJpaAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long> {
}
