package ir.amv.os.vaseline.data.hibernate.search.simple.api.server.dao;

import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;

public interface ITestCountryDao
        extends IBaseSimpleSearchDao<TestCountryEntity, TestCountryDto, Long> {
}
