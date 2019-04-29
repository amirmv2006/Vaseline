package ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.dao;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

public interface ITestCountryDao
        extends IBaseAdvancedSearchDao<TestCountryEntity, ITestCountrySearchObject, Long>,
        IBaseCrudDao<TestCountryEntity, Long> {
}