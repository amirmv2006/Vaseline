package ir.amv.os.vaseline.data.jpa.spring.basic.model;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntityIdNotGenerated<I extends Serializable>
        implements IBasePersistenceModel<I> {

    @Id
    private I id;

}
