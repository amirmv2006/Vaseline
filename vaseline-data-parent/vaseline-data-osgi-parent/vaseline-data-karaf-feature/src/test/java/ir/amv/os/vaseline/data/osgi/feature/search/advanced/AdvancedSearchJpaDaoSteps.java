package ir.amv.os.vaseline.data.osgi.feature.search.advanced;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao.ISampleAdvancedSearchDao;
import ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao.SampleAdvancedSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import org.osgi.service.coordinator.Coordinator;

import javax.inject.Inject;
import javax.transaction.TransactionManager;
import java.util.List;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.doTransactional;
import static ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions.contains;
import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleAdvancedSearchDao.class,
        implClass = SampleAdvancedSearchDao.class
)
public class AdvancedSearchJpaDaoSteps {
    @Inject
    private TransactionManager tm;
    @Inject
    private Coordinator coordinator;
    @Inject
    private ISampleAdvancedSearchDao underTest;

    @And("countBySearchObject works with firstName%={string} and lastName%={string} returns more than {int} records")
    public void countbyexampleWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws Exception {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        Long count = doTransactional(tm, coordinator, () -> underTest.countBySearchObject(searchSample));
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("searchBySearchObject works with firstName%={string} and lastName%={string} contains an entity with firstName={string} and lastName={string}")
    public void searchbyexampleWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws Exception {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        List<SampleEntity> sampleEntities = doTransactional(tm, coordinator, () -> underTest.searchBySearchObject(searchSample));
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }

    @Then("verify AdvancedSearch Dao is registered")
    public void verifyAdvancedSearchDaoIsRegistered() {
        assertNotNull(underTest);
    }
}
