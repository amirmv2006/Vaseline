package ir.amv.os.vaseline.business.osgi.feature.search.advanced;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.osgi.constants.VaselineCoreOsgiConstants;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.ISampleAdvancedSearchApi;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.SampleAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterServiceProperty;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterServicePropertyTypeConstants;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;

import javax.inject.Inject;
import java.util.List;

import static ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions.contains;
import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleAdvancedSearchApi.class,
        implClass = SampleAdvancedSearchApi.class,
        properties = @RegisterServiceProperty(
                propertyName = VaselineCoreOsgiConstants.NEEDS_PROXY,
                propertyType = RegisterServicePropertyTypeConstants.BOOLEAN_PROPERTY_TYPE,
                boolValue = true
        )
)
public class AdvancedSearchApiSteps {
    @Inject
    private ISampleAdvancedSearchApi api;

    @Then("verify AdvancedSearch Business is registered")
    public void verifyAdvancedSearchBusinessIsRegistered() {
        assertNotNull(api);
        assertNotNull(api.getProxy(ISampleAdvancedSearchApi.class));
    }

    @Then("business layer countBySearchObject works with firstName%={string} and lastName%={string} returns more than {int} records")
    public void businessLayerCountBySearchObjectWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws BaseVaselineServerException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        Long count = api.countBySearchObject(searchSample);
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @Then("business layer searchBySearchObject works with firstName%={string} and lastName%={string} contains an entity with firstName={string} and lastName={string}")
    public void businessLayerSearchBySearchObjectWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws BaseVaselineServerException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(criteriaFirstName), contains(criteriaLastName));
        List<SampleEntity> sampleEntities = api.searchBySearchObject(searchSample);
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
