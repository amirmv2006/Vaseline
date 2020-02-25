package ir.amv.os.vaseline.basics.spring.cassandra.layers.model;

import com.datastax.driver.core.DataType;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.AbstractEntityImpl;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.impl.StringId;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

public class BaseStringEntityImpl<I extends StringId<I>, M extends BaseStringEntityImpl<I, M>>
        extends AbstractEntityImpl<I, M> {

    @PrimaryKey("id")
    @CassandraType(type = DataType.Name.TEXT)
    private final I identity;

    public BaseStringEntityImpl(final I identity) {
        this.identity = identity;
    }

    @Override
    public I getIdentity() {
        return identity;
    }

}
