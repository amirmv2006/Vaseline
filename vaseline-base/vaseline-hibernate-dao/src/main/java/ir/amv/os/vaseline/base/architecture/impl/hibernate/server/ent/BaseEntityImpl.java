package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.ent;

import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
@MappedSuperclass
public class BaseEntityImpl<Id extends Serializable> implements IBaseEntity<Id> {

    private Id id;

    @Override
    @javax.persistence.Id
    @GeneratedValue
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? super.hashCode() : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntityImpl<?> other = (BaseEntityImpl<?>) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else
            return id.equals(other.id);
        return super.equals(obj);
    }
}
