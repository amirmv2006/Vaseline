package ir.amv.os.vaseline.service.osgi.feature.basic;

import cucumber.api.java.en.And;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.service.osgi.feature.basic.layer.ISampleBasicService;
import ir.amv.os.vaseline.service.osgi.feature.basic.layer.SampleBasicServiceImpl;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RegisterService;
import org.junit.Assert;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.*;

@RegisterService(
        interfaceClass = ISampleBasicService.class,
        implClass = SampleBasicServiceImpl.class
)
public class BasicServiceSteps {

    @Inject
    private ISampleBasicService underTest;
    private Long id;

    @And("verify Basic Service is registered")
    public void verifyBasicServiceIsRegistered() {
        assertNotNull(underTest);
    }

    @And("service layer save Sample Dto with firstName={string} and lastName={string}")
    public void serivceLayerSaveSampleDtoWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineClientException {
        SampleDto ent = new SampleDto(firstName, lastName);
        id = underTest.save(ent);
        Assert.assertNotNull(id);
    }

    @And("service layer getById return dto with firstName={string} and lastName={string}")
    public void serviceLayerGetByIdReturnDtoWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineClientException {
        SampleDto sampleEntity = underTest.getById(id);
        assertEquals(firstName, sampleEntity.getFirstName());
        assertEquals(lastName, sampleEntity.getLastName());
    }

    @And("service layer count returns more than {int} records")
    public void serviceLayerCountReturnsMoreThanRecords(int minCount) throws BaseVaselineClientException {
        Long count = underTest.countAll();
        assertNotNull(count);
        assertTrue(count > minCount);
    }

    @And("service layer getAll contains a SampleDto with firstName={string} and lastName={string}")
    public void serviceLayerGetAllContainsASampleDtoWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineClientException {
        List<SampleDto> getAll = underTest.getAll();
        assertEquals(1, getAll.size());
        assertTrue(getAll.stream().anyMatch(
                sampleEntity -> sampleEntity.getFirstName().equals(firstName) && sampleEntity.getLastName().equals(lastName)));
    }

    @And("service layer update Sample Dto with firstName={string} and lastName={string}")
    public void serviceLayerUpdateSampleDtoWithFirstNameAndLastName(String firstName, String lastName) throws BaseVaselineClientException {
        SampleDto sampleDto = new SampleDto(firstName, lastName);
        sampleDto.setId(id);
        underTest.update(sampleDto);
    }
}
