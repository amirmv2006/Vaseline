package ir.amv.os.vaseline.data.jpa.search.simple.api.server.dao;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;

public interface ITestCountryDao
        extends IBaseCrudDao<Long, TestCountryEntity>,
        IBaseSimpleSearchDao<Long, TestCountryEntity, TestCountryDto> {
}
