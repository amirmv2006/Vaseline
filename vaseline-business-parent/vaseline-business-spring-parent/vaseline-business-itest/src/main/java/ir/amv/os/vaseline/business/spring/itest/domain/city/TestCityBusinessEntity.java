package ir.amv.os.vaseline.business.spring.itest.domain.city;


import ir.amv.os.vaseline.business.spring.itest.domain.base.BaseBusinessEntityImpl;
import ir.amv.os.vaseline.business.spring.itest.domain.state.TestStateBusinessEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestCityBusinessEntity
        extends BaseBusinessEntityImpl<Long> {

    private String cityName;
    private TestStateBusinessEntity state;

}
