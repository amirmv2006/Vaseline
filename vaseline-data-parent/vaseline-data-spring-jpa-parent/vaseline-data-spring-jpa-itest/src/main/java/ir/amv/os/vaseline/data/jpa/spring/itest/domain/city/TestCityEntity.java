package ir.amv.os.vaseline.data.jpa.spring.itest.domain.city;

import ir.amv.os.vaseline.data.jpa.spring.basic.model.BaseEntityImpl;
import ir.amv.os.vaseline.data.jpa.spring.itest.domain.state.TestStateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Access(AccessType.FIELD)
@Table(name = "TST_CITY")
public class TestCityEntity
    extends BaseEntityImpl<Long> {

    @Column(name = "CITY_NAME", unique = true)
    private String cityName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_ID")
    private TestStateEntity state;
}