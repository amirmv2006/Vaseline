package ir.amv.os.vaseline.service.osgi.feature.search.advanced;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.service.osgi.feature.search.advanced.layer.ISampleAdvancedSearchService;
import ir.amv.os.vaseline.service.osgi.feature.search.advanced.layer.SampleAdvancedSearchServiceImpl;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;

import javax.inject.Inject;
import java.util.List;

import static ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions.contains;
import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleAdvancedSearchService.class,
        implClass = SampleAdvancedSearchServiceImpl.class
)
public class AdvancedSearchServiceSteps {

    @Inject
    private ISampleAdvancedSearchService underTest;

    @Then("verify AdvancedSearch Service is registered")
    public void verifyAdvancedSearchServiceIsRegistered() {
        assertNotNull(underTest);
    }

    @And("service layer countBySearchObject works with firstName%={string} and lastName%={string} returns more than {int} records")
    public void serviceLayerCountBySearchObjectWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws BaseVaselineClientException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        Long count = underTest.countBySearchObject(searchSample);
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("service layer searchBySearchObject works with firstName%={string} and lastName%={string} contains a dto with firstName={string} and lastName={string}")
    public void serviceLayerSearchBySearchObjectWorksWithFirstNameAndLastNameContainsADtoWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws BaseVaselineClientException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(criteriaFirstName), contains(criteriaLastName));
        List<SampleDto> sampleEntities = underTest.searchBySearchObject(searchSample);
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
