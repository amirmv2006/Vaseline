package ir.amv.os.vaseline.business.osgi.feature.search.simple;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.osgi.feature.search.simple.layer.ISampleSimpleSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RequireClassRemotely(ISampleSimpleSearchApi.class)
public class SimpleSearchApiSteps {

    @Inject
    private ISampleSimpleSearchApi api;

    @And("business layer countByExample works with firstName={string} and lastName={string} returns more than {int} records")
    public void businessLayerCountByExampleWorksWithFirstNameAndLastNameReturnsMoreThanRecords(String firstName, String lastName, int minCount) throws BaseVaselineServerException {
        SampleDto searchSample = new SampleDto(firstName, lastName);
        Long count = api.countByExample(searchSample);
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("business layer searchByExample works with firstName={string} and lastName={string} contains an entity with firstName={string} and lastName={string}")
    public void businessLayerSearchByExampleWorksWithFirstNameAndLastNameContainsAnEntityWithFirstNameAndLastName(String criteriaFirstName, String criteriaLastName, String firstName, String lastName) throws BaseVaselineServerException {
        SampleDto searchSample = new SampleDto(criteriaFirstName, criteriaLastName);
        List<SampleEntity> sampleEntities = api.searchByExample(searchSample);
        assertEquals(1, sampleEntities.size());
        assertTrue(sampleEntities.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }
}
