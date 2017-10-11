package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;
import ir.amv.os.vaseline.data.test.model.shared.searchobject.ITestCountrySearchObject;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        extends BaseJpaAdvancedSearchDaoImpl<TestCountryEntity, TestCountryDto, ITestCountrySearchObject, Long>
        implements ITestCountryDao {

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
