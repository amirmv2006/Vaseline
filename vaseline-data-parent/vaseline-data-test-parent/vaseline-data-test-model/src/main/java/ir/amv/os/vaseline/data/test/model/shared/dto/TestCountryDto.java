package ir.amv.os.vaseline.data.test.model.shared.dto;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.BaseDtoImpl;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

public class TestCountryDto
        extends BaseDtoImpl<Long> {

    private String countryName;
    private Long population;
    private Boolean areRacist;
    private Date birthDate;

    private TestContinentDto continent;
    private Set<TestStateDto> states;

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

    public TestContinentDto getContinent() {
        return continent;
    }

    public void setContinent(TestContinentDto continent) {
        this.continent = continent;
    }

    public Set<TestStateDto> getStates() {
        return states;
    }

    public void setStates(Set<TestStateDto> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestCountryDto that = (TestCountryDto) o;
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
