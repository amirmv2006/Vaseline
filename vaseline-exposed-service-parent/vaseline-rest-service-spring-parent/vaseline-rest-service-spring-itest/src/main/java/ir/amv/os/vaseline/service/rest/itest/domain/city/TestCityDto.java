package ir.amv.os.vaseline.service.rest.itest.domain.city;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.BaseDtoImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TestCityDto
        extends BaseDtoImpl<Long> {

    private String cityName;

}
