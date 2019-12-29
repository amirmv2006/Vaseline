package ir.amv.os.vaseline.data.jpa.spring.basic.model;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * @author Amir
 */
@MappedSuperclass
public class BaseUUIDEntityImpl implements IBasePersistenceModel<String> {
    @Id
    @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseUUIDEntityImpl that = (BaseUUIDEntityImpl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}