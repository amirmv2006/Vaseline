package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by amv on 12/17/16.
 */
@Entity
public class TestState extends TestBaseEntity<Long> {

    private String stateName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state", fetch = FetchType.EAGER)
    private Set<TestCity> cities;
    @ManyToOne
    private TestCountry country;

    public String getStateName() {

        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Set<TestCity> getCities() {
        return cities;
    }

    public void setCities(Set<TestCity> cities) {
        this.cities = cities;
    }

    public TestCountry getCountry() {
        return country;
    }

    public void setCountry(TestCountry country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TestState testState = (TestState) o;

        return stateName != null ? stateName.equals(testState.stateName) : testState.stateName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (stateName != null ? stateName.hashCode() : 0);
        return result;
    }
}
