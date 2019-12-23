package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.model.BaseEntityImpl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class TestContinentBusinessModel
        extends BaseEntityImpl<Long> {

    @Column(name = "CONTINENT_NAME", unique = true)
    private String continentName;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestContinentBusinessModel that = (TestContinentBusinessModel) o;
        return Objects.equals(continentName, that.continentName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), continentName);
    }
}
