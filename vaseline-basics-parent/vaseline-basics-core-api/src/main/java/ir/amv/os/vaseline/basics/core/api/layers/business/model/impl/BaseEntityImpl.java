package ir.amv.os.vaseline.basics.core.api.layers.business.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseModel;

import java.util.Objects;

public class BaseEntityImpl<I extends IBaseModel<I>, M extends IBaseEntity<I, M>>
        implements IBaseEntity<I, M> {

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
