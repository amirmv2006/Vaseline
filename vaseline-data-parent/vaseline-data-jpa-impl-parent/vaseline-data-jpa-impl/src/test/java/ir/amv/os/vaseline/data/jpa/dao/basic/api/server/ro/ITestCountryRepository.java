package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro;

import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;

public interface ITestCountryRepository
        extends IBasePersistentModelRepository<Long, TestCountryEntity> {
}
