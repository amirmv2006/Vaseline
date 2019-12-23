package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.model.BaseBusinessModelImpl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
public class TestStateBusinessModel
        extends BaseBusinessModelImpl<Long> {

    @Column(unique = true)
    private String stateName;
    @OneToMany
    private Set<TestCityBusinessModel> cities;

    public Set<TestCityBusinessModel> getCities() {
        return cities;
    }

    public void setCities(Set<TestCityBusinessModel> cities) {
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
        TestStateBusinessModel that = (TestStateBusinessModel) o;
        return Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), stateName, cities);
    }
}
