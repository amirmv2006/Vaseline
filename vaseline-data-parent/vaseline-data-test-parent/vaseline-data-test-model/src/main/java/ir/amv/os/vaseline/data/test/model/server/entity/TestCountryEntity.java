package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

@Entity
public class TestCountryEntity
        extends BaseEntityImpl<Long> {

    private String countryName;
    private Long population;
    private Boolean areRacist;
    private Date birthDate;

    @ManyToOne
    private TestContinentEntity continent;
    @OneToMany
    private Set<TestCityEntity> cities;

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

    public TestContinentEntity getContinent() {
        return continent;
    }

    public void setContinent(TestContinentEntity continent) {
        this.continent = continent;
    }

    public Set<TestCityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<TestCityEntity> cities) {
        this.cities = cities;
    }
}
