package ir.amv.os.vaseline.data.jpa.spring.itest.domain.state;

import ir.amv.os.vaseline.data.jpa.spring.basic.model.BaseEntityImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table(name = "TST_STATE")
public class TestStateEntity
        extends BaseEntityImpl<Long> {

    @Column(name = "STATE_NAME", unique = true)
    private String stateName;
    @OneToMany
    private Set<TestCityEntity> cities;

}
