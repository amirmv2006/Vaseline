package ir.amv.os.vaseline.business.spring.itest.domain.city;


import ir.amv.os.vaseline.business.spring.itest.domain.base.BaseBusinessModelImpl;
import ir.amv.os.vaseline.business.spring.itest.domain.state.TestStateBusinessModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestCityBusinessModel
        extends BaseBusinessModelImpl<Long> {

    private String cityName;
    private TestStateBusinessModel state;

}
