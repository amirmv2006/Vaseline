package ir.amv.os.vaseline.bpm.secured.api.base.impl.server.base;

import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.bpm.api.server.base.api.crud.IBaseBpmCrudApi;
import ir.amv.os.vaseline.security.authorization.api.shared.base.api.crud.IBaseSecuredCrudApi;

import java.io.Serializable;

/**
 * Created by AMV on 11/10/2015.
 */
public interface IBaseBpmSecuredCrudApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseSecuredCrudApi<E, D, Id>, IBaseBpmCrudApi<E, D, Id> {

    Id saveBpmNotSecured(E entity, String taskId) throws BaseVaselineServerException;

}
