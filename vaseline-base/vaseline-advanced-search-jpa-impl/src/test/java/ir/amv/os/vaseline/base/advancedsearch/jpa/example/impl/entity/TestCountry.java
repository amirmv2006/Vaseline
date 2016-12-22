package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by amv on 12/17/16.
 */
@Entity
public class TestCountry extends TestBaseEntity<Long> {

    private String countryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<TestState> states;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Set<TestState> getStates() {
        return states;
    }

    public void setStates(Set<TestState> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TestCountry that = (TestCountry) o;

        return countryName != null ? countryName.equals(that.countryName) : that.countryName == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (countryName != null ? countryName.hashCode() : 0);
        return result;
    }
}
