package ir.amv.os.vaseline.data.itest.server.entity;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class TestContinentEntity
        implements IBasePersistenceModel<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "CONTINENT_NAME", unique = true)
    private String continentName;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
        TestContinentEntity that = (TestContinentEntity) o;
        return Objects.equals(continentName, that.continentName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), continentName);
    }
}
