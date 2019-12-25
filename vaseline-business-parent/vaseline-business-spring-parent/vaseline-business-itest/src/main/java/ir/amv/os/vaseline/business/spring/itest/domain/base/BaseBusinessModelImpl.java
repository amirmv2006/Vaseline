package ir.amv.os.vaseline.business.spring.itest.domain.base;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;

public class BaseBusinessModelImpl<I>
        implements IBaseBusinessModel<I> {

    private I id;

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }
}
