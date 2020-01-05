package ir.amv.os.vaseline.business.spring.itest.domain.base;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import lombok.Data;

@Data
public class BaseBusinessEntityImpl<I>
        implements IBaseBusinessEntity<I, M> {

    private I id;

}
