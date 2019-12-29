package ir.amv.os.vaseline.business.spring.itest.domain.base;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import lombok.Data;

@Data
public class BaseBusinessModelImpl<I>
        implements IBaseBusinessModel<I> {

    private I id;

}
