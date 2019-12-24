package ir.amv.os.vaseline.data.itest.server.entity;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
public class TestStateEntity
        implements IBasePersistenceModel<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String stateName;
    @OneToMany
    private Set<TestCityEntity> cities;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Set<TestCityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<TestCityEntity> cities) {
        this.cities = cities;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestStateEntity that = (TestStateEntity) o;
        return Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), stateName, cities);
    }
}
