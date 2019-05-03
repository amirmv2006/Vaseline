package ir.amv.os.vaseline.data.osgi.feature.search.simple;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.SampleSimpleSearchDao;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import org.osgi.framework.BundleContext;
import org.osgi.service.coordinator.Coordinator;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.TransactionManager;
import java.util.List;

import static ir.amv.os.vaseline.data.osgi.feature.helper.VaselineDataHelper.doTransactional;
import static org.junit.Assert.*;

public class SimpleSearchSteps {

    @Inject
    private EntityManager em;
    @Inject
    private TransactionManager tm;
    @Inject
    private Coordinator coordinator;
    @Inject
    private BundleContext bundleContext;

    private IBaseSimpleSearchDao<SampleEntity, SampleDto, Long> underTest;

    @When("^register Sample Simple Search Dao$")
    public void registerSampleDao() {
        underTest = new SampleSimpleSearchDao(em);
    }

    @Then("searchByExample works with firstName={string} and lastName={string}")
    public void searchbyexampleWorksWithFirstNameAndLastName(String firstName, String lastName) throws Exception {
    }

    @And("countByExample works with firstName={string} and lastName={string} returns more than {int} records")
    public void countbyexampleWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws Exception {
        SampleDto searchSample = new SampleDto(firstName, lastName);
        Long count = doTransactional(tm, coordinator, () -> underTest.countByExample(searchSample));
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("searchByExample works with firstName={string} and lastName={string} contains an entity with firstName={string} and lastName={string}")
    public void searchbyexampleWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws Exception {
        SampleDto searchSample = new SampleDto(firstName, lastName);
        List<SampleEntity> sampleEntities = doTransactional(tm, coordinator, () -> underTest.searchByExample(searchSample));
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
