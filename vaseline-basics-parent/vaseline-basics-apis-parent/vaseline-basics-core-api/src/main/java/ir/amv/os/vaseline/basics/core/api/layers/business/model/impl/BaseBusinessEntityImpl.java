package ir.amv.os.vaseline.basics.core.api.layers.business.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;

import java.util.Objects;

public class BaseBusinessEntityImpl<I extends IBaseBusinessModel<I>, M extends IBaseBusinessEntity<I, M>>
        implements IBaseBusinessEntity<I, M> {

    private I id;

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void setId(I id) {
        this.id = id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M that = (M) o;
        return isSameAs(that);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
