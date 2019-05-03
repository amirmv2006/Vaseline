package ir.amv.os.vaseline.data.osgi.test.jpa.model;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseEntityImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleEntity extends BaseEntityImpl<Long> {

    private String firstName;
    private String lastName;

}
