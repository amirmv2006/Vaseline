package ir.amv.os.vaseline.business.osgi.feature.search.advanced;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer.ISampleAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;

import javax.inject.Inject;
import java.util.List;

import static ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.PropertyConditions.contains;
import static org.junit.Assert.*;

@RequireClassRemotely(ISampleAdvancedSearchApi.class)
public class AdvancedSearchApiSteps {
    @Inject
    private ISampleAdvancedSearchApi api;

    @And("business layer countBySearchObject works with firstName%={string} and lastName%={string} returns more than {int} records")
    public void businessLayerCountBySearchObjectWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws BaseVaselineServerException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        Long count = api.countBySearchObject(searchSample);
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("business layer searchBySearchObject works with firstName%={string} and lastName%={string} contains an entity with firstName={string} and lastName={string}")
    public void businessLayerSearchBySearchObjectWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws BaseVaselineServerException {
        SampleSearchObject searchSample = new SampleSearchObject(contains(firstName), contains(lastName));
        List<SampleEntity> sampleEntities = api.searchBySearchObject(searchSample);
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
