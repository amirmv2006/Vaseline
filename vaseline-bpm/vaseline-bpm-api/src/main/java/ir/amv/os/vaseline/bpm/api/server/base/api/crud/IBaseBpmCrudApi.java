package ir.amv.os.vaseline.bpm.api.server.base.api.crud;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

/**
 * Created by AMV on 11/9/2015.
 */
public interface IBaseBpmCrudApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseCrudApi<E, D, Id> {

    Id saveBpm(E entity, String taskId) throws BaseVaselineServerException;

    String getIdVariableName();
}
