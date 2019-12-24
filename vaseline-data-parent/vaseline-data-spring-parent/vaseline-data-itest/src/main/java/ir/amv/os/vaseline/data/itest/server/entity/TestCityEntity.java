package ir.amv.os.vaseline.data.itest.server.entity;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class TestCityEntity
    implements IBasePersistenceModel<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
