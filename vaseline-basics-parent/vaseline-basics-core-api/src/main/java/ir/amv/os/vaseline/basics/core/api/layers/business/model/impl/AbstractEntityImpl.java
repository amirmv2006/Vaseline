package ir.amv.os.vaseline.basics.core.api.layers.business.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseEntity;

import java.util.Objects;

public abstract class AbstractEntityImpl<I, M extends AbstractEntityImpl<I, M>>
        implements IBaseEntity<I, M> {

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M that = (M) o;
        return isSameAs(that);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getIdentity());
    }

}
