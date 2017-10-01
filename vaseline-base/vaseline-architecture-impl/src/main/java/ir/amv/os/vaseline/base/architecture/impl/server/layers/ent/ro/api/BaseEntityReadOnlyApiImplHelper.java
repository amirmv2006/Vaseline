package ir.amv.os.vaseline.base.architecture.impl.server.layers.ent.ro.api;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 2/9/2016.
 */
public class BaseEntityReadOnlyApiImplHelper {

    public static <E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable> void postGet(
            E entity)
            throws BaseVaselineServerException {
    }
}
