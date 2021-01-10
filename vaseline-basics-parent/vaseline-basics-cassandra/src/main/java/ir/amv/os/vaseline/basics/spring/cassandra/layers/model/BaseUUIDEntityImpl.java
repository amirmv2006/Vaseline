package ir.amv.os.vaseline.basics.spring.cassandra.layers.model;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.AbstractEntityImpl;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.EntityUUID;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class BaseUUIDEntityImpl<I extends EntityUUID<I>, M extends BaseUUIDEntityImpl<I, M>>
        extends AbstractEntityImpl<I, M> {

    @PrimaryKey("id")
    @CassandraType(type = CassandraType.Name.UUID)
    private final I identity;

    public BaseUUIDEntityImpl(final I identity) {
        this.identity = identity;
    }

    @Override
    public I getIdentity() {
        return identity;
    }

}
