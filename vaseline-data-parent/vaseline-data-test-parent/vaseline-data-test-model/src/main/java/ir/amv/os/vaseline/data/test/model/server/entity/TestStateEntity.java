package ir.amv.os.vaseline.data.test.model.server.entity;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;

import javax.persistence.Entity;

@Entity
public class TestStateEntity
        extends BaseEntityImpl<Long> {

    private String stateName;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
