package ir.amv.os.vaseline.business.spring.itest.domain.state;

import ir.amv.os.vaseline.business.spring.itest.domain.base.BaseBusinessModelImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestStateBusinessModel
        extends BaseBusinessModelImpl<Long> {

    private String stateName;

}
