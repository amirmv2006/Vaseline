package ir.amv.os.vaseline.data.test.model.shared.dto;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseDtoImpl;

import java.util.Objects;

public class TestContinentDto
        extends BaseDtoImpl<Long> {

    private String continentName;

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
        TestContinentDto that = (TestContinentDto) o;
        return Objects.equals(continentName, that.continentName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), continentName);
    }
}
