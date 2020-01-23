package ir.amv.os.vaseline.basics.core.api.layers.persistent.model;

import java.io.Serializable;

public interface IBasePersistenceModel<I extends Serializable> {

    I getId();
    void setId(I id);

}
