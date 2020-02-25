package ir.amv.os.vaseline.basics.core.api.layers.business.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseValueObject;

public abstract class BaseValueObjectImpl<M extends BaseValueObjectImpl<M>>
        implements IBaseValueObject<M> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        M generate = (M) o;
        return isSameAs(generate);
    }

    @Override
    public abstract int hashCode();
}
