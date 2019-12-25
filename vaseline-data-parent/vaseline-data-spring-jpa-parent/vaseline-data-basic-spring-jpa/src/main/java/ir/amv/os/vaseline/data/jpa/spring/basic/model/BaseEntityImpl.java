package ir.amv.os.vaseline.data.jpa.spring.basic.model;


import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
@MappedSuperclass
@Data
public class BaseEntityImpl<I extends Serializable> implements IBasePersistenceModel<I> {

    @Id
    @GeneratedValue
    private I id;

}
