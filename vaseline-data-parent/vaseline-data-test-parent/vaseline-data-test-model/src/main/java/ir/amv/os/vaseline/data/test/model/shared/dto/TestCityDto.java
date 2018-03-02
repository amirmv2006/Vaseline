package ir.amv.os.vaseline.data.test.model.shared.dto;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseDtoImpl;

import java.util.Objects;

public class TestCityDto
    extends BaseDtoImpl<Long> {

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TestCityDto that = (TestCityDto) o;
        return Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), cityName);
    }
}
