package ir.amv.os.vaseline.basics.spring.cassandra.layers.model;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.AbstractEntityImpl;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class BaseCustomPrimaryKeyEntityImpl<I, M extends BaseCustomPrimaryKeyEntityImpl<I, M>>
        extends AbstractEntityImpl<I, M> {

    @PrimaryKey("id")
    private final I identity;

    public BaseCustomPrimaryKeyEntityImpl(final I identity) {
        this.identity = identity;
    }

    @Override
    public I getIdentity() {
        return identity;
    }

}
