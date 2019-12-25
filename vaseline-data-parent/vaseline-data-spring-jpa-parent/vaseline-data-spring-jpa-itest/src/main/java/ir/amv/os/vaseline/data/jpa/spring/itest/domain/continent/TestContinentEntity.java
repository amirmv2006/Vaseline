package ir.amv.os.vaseline.data.jpa.spring.itest.domain.continent;

import ir.amv.os.vaseline.data.jpa.spring.basic.model.BaseEntityImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table(name = "TST_CONTINENT")
public class TestContinentEntity
        extends BaseEntityImpl<Long> {

    @Column(name = "CONTINENT_NAME", unique = true)
    private String continentName;

}
