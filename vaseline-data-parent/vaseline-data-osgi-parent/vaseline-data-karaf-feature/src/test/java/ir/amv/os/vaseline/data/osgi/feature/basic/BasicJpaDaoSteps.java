package ir.amv.os.vaseline.data.osgi.feature.basic;

import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.data.osgi.feature.basic.dao.ISampleBasicDao;
import ir.amv.os.vaseline.data.osgi.feature.basic.dao.SampleBasicDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import org.osgi.service.coordinator.Coordinator;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import java.util.List;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.doTransactional;
import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleBasicDao.class,
        implClass = SampleBasicDao.class
)
public class BasicJpaDaoSteps {

    @Inject
    private TransactionManager tm;
    @Inject
    private Coordinator coordinator;
    @Inject
    private ISampleBasicDao underTest;

    private Long id;

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
