package ir.amv.os.vaseline.basics.core.api.layers.business.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseValueObject;

import java.util.Objects;

public class StringId<M extends StringId<M>> implements IBaseValueObject<M> {

    private final String id;

    public StringId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean isSameAs(M other) {
        return id.equals(other.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M that = (M) o;
        return isSameAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
