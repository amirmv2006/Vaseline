package ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.dao;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryBusinessModel;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

public interface ITestCountryDao
        extends IBaseAdvancedSearchRepository<Long, TestCountryBusinessModel, ITestCountrySearchObject>,
        IBaseCrudDao<Long, TestCountryBusinessModel> {
}
