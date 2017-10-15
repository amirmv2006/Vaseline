package ir.amv.os.vaseline.business.apis.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.layer.server.base.IBaseApi;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseEntityReadOnlyApi;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseImplementedEntityReadOnlyApi<E extends IBaseEntity<?>>
        extends IBaseEntityReadOnlyApi<E> {
}
