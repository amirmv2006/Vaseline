package ir.amv.os.vaseline.business.spring.itest.domain.state;

import ir.amv.os.vaseline.business.spring.itest.domain.base.BaseBusinessEntityImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestStateBusinessEntity
        extends BaseBusinessEntityImpl<Long> {

    private String stateName;

}
