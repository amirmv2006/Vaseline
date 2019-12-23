package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.model.BaseEntityImpl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Access(AccessType.FIELD)
public class TestCountryBusinessModel
        extends BaseEntityImpl<Long> {

    @Column(unique = true)
    private String countryName;
    private Long population;
    private Boolean areRacist;
    private Date birthDate;

    @ManyToOne
    private TestContinentBusinessModel continent;
    @OneToMany
    private Set<TestStateBusinessModel> states;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Boolean getAreRacist() {
        return areRacist;
    }

    public void setAreRacist(Boolean areRacist) {
        this.areRacist = areRacist;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public TestContinentBusinessModel getContinent() {
        return continent;
    }

    public void setContinent(TestContinentBusinessModel continent) {
        this.continent = continent;
    }

    public Set<TestStateBusinessModel> getStates() {
        return states;
    }

    public void setStates(Set<TestStateBusinessModel> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestCountryBusinessModel that = (TestCountryBusinessModel) o;
        return Objects.equals(countryName, that.countryName) &&
                Objects.equals(population, that.population) &&
                Objects.equals(areRacist, that.areRacist) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(continent, that.continent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), countryName, population, areRacist, birthDate, continent, states);
    }
}
