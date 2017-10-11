package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.data.test.model.server.entity.TestCountryEntity;
import ir.amv.os.vaseline.data.test.model.shared.dto.TestCountryDto;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class TestCountryDaoImpl
        extends BaseJpaSimpleSearchDaoImpl<TestCountryEntity, TestCountryDto, Long>
        implements ITestCountryDao {

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
}
