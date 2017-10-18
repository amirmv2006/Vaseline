package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

public interface ITestCountryDao
        extends IBaseAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long>,
        IBaseCrudDao<TestCountryEntity, Long> {
}
