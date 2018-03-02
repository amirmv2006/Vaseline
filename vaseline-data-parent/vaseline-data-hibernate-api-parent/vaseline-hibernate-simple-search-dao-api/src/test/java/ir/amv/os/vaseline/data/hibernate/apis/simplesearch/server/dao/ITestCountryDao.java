package ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;

public interface ITestCountryDao
        extends IBaseSimpleSearchDao<TestCountryEntity, TestCountryDto, Long> {
}
