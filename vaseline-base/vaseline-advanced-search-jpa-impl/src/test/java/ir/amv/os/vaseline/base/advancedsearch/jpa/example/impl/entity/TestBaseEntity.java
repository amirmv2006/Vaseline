package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by amv on 12/17/16.
 */
@MappedSuperclass
public class TestBaseEntity<Id extends Serializable> implements Serializable {

    @javax.persistence.Id
    @GeneratedValue
    private Id id;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestBaseEntity<?> that = (TestBaseEntity<?>) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id == null ? super.hashCode() : id.hashCode();
    }
}
