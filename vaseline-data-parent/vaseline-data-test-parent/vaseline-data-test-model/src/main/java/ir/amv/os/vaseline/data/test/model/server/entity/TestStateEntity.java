package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class TestStateEntity
        extends BaseEntityImpl<Long> {

    @Column(unique = true)
    private String stateName;
    @OneToMany
    private Set<TestCityEntity> cities;

    public Set<TestCityEntity> getCities() {
        return cities;
    }

    public void setCities(Set<TestCityEntity> cities) {
        this.cities = cities;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
