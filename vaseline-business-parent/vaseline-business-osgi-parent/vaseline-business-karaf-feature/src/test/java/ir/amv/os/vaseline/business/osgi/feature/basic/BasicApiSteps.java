package ir.amv.os.vaseline.business.osgi.feature.basic;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.osgi.feature.basic.layer.ISampleBasicApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RequireClassRemotely;
import org.junit.Assert;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RequireClassRemotely(ISampleBasicApi.class)
public class BasicApiSteps {

    @Inject
    private ISampleBasicApi sampleApi;

    private Long id;

    @Then("business layer save Sample Entity with firstName={string} and lastName={string}")
    public void businessLayerSaveSampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineServerException {
        SampleEntity ent = new SampleEntity(firstName, lastName);
        id = sampleApi.save(ent);
        Assert.assertNotNull(id);
    }

    @And("business layer getById return entity with firstName={string} and lastName={string}")
    public void businessLayerGetByIdReturnEntityWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineServerException {
        SampleEntity sampleEntity = sampleApi.getById(id);
        assertEquals(firstName, sampleEntity.getFirstName());
        assertEquals(lastName, sampleEntity.getLastName());
    }

    @And("business layer count returns more than {int} records")
    public void businessLayerCountReturnsMoreThanRecords(int minCount) throws BaseVaselineServerException {
        Long count = sampleApi.countAll();
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("business layer getAll contains a SampleEntity with firstName={string} and lastName={string}")
    public void businessLayerGetAllContainsASampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineServerException {
        List<SampleEntity> getAll = sampleApi.getAll();
        assertEquals(1, getAll.size());
        assertTrue(getAll.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }

    @And("business layer update Sample Entity with firstName={string} and lastName={string}")
    public void businessLayerUpdateSampleEntityWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineServerException {
        SampleEntity sampleEntity = new SampleEntity(firstName, lastName);
        sampleEntity.setId(id);
        sampleApi.update(sampleEntity);
    }
}
