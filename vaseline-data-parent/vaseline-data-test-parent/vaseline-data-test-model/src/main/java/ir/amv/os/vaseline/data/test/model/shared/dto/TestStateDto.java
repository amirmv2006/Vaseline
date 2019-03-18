package ir.amv.os.vaseline.data.test.model.shared.dto;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.baseimpl.BaseDtoImpl;

import java.util.Objects;
import java.util.Set;

public class TestStateDto
        extends BaseDtoImpl<Long> {

    private String stateName;
    private Set<TestCityDto> cities;

    public Set<TestCityDto> getCities() {
        return cities;
    }

    public void setCities(Set<TestCityDto> cities) {
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
        TestStateDto that = (TestStateDto) o;
        return Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), stateName, cities);
    }
}
