package ir.amv.os.vaseline.data.jpa.spring.itest.domain.state;

import ir.amv.os.vaseline.data.jpa.spring.basic.model.BaseEntityImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.city.TestCityEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.country.TestCountryEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private TestCountryEntity country;
    @OneToMany(mappedBy = "state")
    private Set<TestCityEntity> cities;

}
