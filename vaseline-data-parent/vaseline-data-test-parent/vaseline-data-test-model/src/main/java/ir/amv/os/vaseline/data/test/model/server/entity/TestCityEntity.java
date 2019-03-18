package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseEntityImpl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class TestCityEntity
    extends BaseEntityImpl<Long> {

    @Column(unique = true)
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
        TestCityEntity that = (TestCityEntity) o;
        return Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), cityName);
    }
}
