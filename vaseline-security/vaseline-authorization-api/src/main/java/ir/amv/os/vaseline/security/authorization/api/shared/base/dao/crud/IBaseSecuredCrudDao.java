package ir.amv.os.vaseline.security.authorization.api.shared.base.dao.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authorization.api.shared.base.dao.ro.IBaseSecuredReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;

import java.io.Serializable;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredCrudDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends ISecurityCriteria>
        extends IBaseCrudDao<E, Id>, IBaseSecuredReadOnlyDao<E, D, Id, SecurityCriteria> {

}
