package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;

public interface ITestCountryDao
        extends IBaseReadOnlyDao<TestCountryEntity, Long> {
}