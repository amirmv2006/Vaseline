package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TestContinentEntity
        extends BaseEntityImpl<Long> {

    @Column(name = "CONTINENT_NAME", unique = true)
    private String continentName;

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
}
