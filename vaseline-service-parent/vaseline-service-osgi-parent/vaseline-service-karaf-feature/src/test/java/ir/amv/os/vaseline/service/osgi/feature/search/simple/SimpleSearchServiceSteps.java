package ir.amv.os.vaseline.service.osgi.feature.search.simple;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.service.osgi.feature.search.simple.layer.ISampleSimpleSearchService;
import ir.amv.os.vaseline.service.osgi.feature.search.simple.layer.SampleSimpleSearchServiceImpl;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleSimpleSearchService.class,
        implClass = SampleSimpleSearchServiceImpl.class
)
public class SimpleSearchServiceSteps {

    @Inject
    private ISampleSimpleSearchService underTest;

    @And("verify SimpleSearch Service is registered")
    public void verifySimpleSearchServiceIsRegistered() {
        assertNotNull(underTest);
    }

    @And("service layer countByExample works with firstName={string} and lastName={string} returns more than {int} records")
    public void serviceLayerCountByExampleWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws BaseVaselineClientException {
        SampleDto searchSample = new SampleDto(firstName, lastName);
        Long count = underTest.countByExample(searchSample);
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("service layer searchByExample works with firstName={string} and lastName={string} contains a dto with firstName={string} and lastName={string}")
    public void serviceLayerSearchByExampleWorksWithFirstNameAndLastNameContainsADtoWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws BaseVaselineClientException {
        SampleDto searchSample = new SampleDto(criteriaFirstName, criteriaLastName);
        List<SampleDto> sampleEntities = underTest.searchByExample(searchSample);
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
