package ir.amv.os.vaseline.data.osgi.feature.search.simple;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.ISampleSimpleSearchDao;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.SampleSimpleSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import org.osgi.service.coordinator.Coordinator;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import java.util.List;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.doTransactional;
import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleSimpleSearchDao.class,
        implClass = SampleSimpleSearchDao.class
)
public class SimpleSearchJpaDaoSteps {

    @Inject
    private TransactionManager tm;
    @Inject
    private Coordinator coordinator;
    @Inject
    private ISampleSimpleSearchDao underTest;

    @And("countByExample works with firstName={string} and lastName={string} returns more than {int} records")
    public void countbyexampleWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws Exception {
        SampleDto searchSample = new SampleDto(firstName, lastName);
        Long count = doTransactional(tm, coordinator, () -> underTest.countByExample(searchSample));
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("searchByExample works with firstName={string} and lastName={string} contains an entity with firstName={string} and lastName={string}")
    public void searchbyexampleWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws Exception {
        SampleDto searchSample = new SampleDto(criteriaFirstName, criteriaLastName);
        List<SampleEntity> sampleEntities = doTransactional(tm, coordinator, () -> underTest.searchByExample(searchSample));
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
