package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;

public interface ITestCountryDao
        extends IBaseImplementedJpaSimpleSearchDao<TestCountryEntity, TestCountryDto, Long> {
}
