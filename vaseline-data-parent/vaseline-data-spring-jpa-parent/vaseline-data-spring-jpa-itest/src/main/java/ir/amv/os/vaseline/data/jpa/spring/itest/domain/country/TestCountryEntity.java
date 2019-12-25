package ir.amv.os.vaseline.data.jpa.spring.itest.domain.country;

import ir.amv.os.vaseline.data.jpa.spring.basic.model.BaseEntityImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.continent.TestContinentEntity;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.state.TestStateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table(name = "TST_COUNTRY")
public class TestCountryEntity
        extends BaseEntityImpl<Long> {

    @Column(name = "COUNTRY_NAME", unique = true)
    private String countryName;
    @Column(name = "POPULATION")
    private Long population;
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @ManyToOne
    private TestContinentEntity continent;
    @OneToMany
    private Set<TestStateEntity> states;

}
