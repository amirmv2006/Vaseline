package ir.amv.os.vaseline.data.osgi.feature.basic;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.osgi.feature.basic.dao.SampleBasicDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import org.osgi.service.coordinator.Coordinator;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import java.util.List;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.doTransactional;
import static org.junit.Assert.*;

public class JpaBasicDaoSteps {

    @Inject
    private EntityManager em;
    @Inject
    private TransactionManager tm;
    @Inject
    private Coordinator coordinator;

    private IBaseCrudDao<SampleEntity, Long> underTest;
    private Long id;

    @When("^register Sample Basic Dao$")
    public void registerSampleDao() {
        underTest = new SampleBasicDao(em);
    }

    @Then("save Sample Entity with firstName={string} and lastName={string}")
    public void saveSampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws Exception {
        SampleEntity ent = new SampleEntity(firstName, lastName);
        id = doTransactional(tm, coordinator, () -> underTest.save(ent));
        assertNotNull(id);
    }

    @Then("getById return entity with firstName={string} and lastName={string}")
    public void getByIdReturnEntityWithFirstNameAndLastName(String firstName, String lastName) throws Exception {
        SampleEntity sampleEntity = doTransactional(tm, coordinator, () -> underTest.getById(id));
        assertEquals(firstName, sampleEntity.getFirstName());
        assertEquals(lastName, sampleEntity.getLastName());
    }

    @Then("count returns more than {int} records")
    public void countRetunsMoreThanRecords(int minCount) throws Exception {
        Long count = doTransactional(tm, coordinator, () -> underTest.countAll());
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @Then("getAll contains a SampleEntity with firstName={string} and lastName={string}")
    public void getallContainsASampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws Exception {
        List<SampleEntity> getAll = doTransactional(tm, coordinator, () -> underTest.getAll());
        assertEquals(1, getAll.size());
        assertTrue(getAll.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }

    @Then("update Sample Entity with firstName={string} and lastName={string}")
    public void updateSampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws Exception {
        SampleEntity sampleEntity = new SampleEntity(firstName, lastName);
        sampleEntity.setId(id);
        doTransactional(tm, coordinator, () -> {
            underTest.update(sampleEntity);
            return null;
        });
    }
}
