package ir.amv.os.vaseline.security.authorization.api.shared.base.dao.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.criteria.ISecurityCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredReadOnlyDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends ISecurityCriteria>
        extends IBaseReadOnlyDao<E, Id> {

    E getById(Id id, SecurityCriteria securityCriteria);
    E getByIdDetached(Id id, SecurityCriteria securityCriteria);

    Long countAll(SecurityCriteria securityCriteria);
    List<E> getAll(SecurityCriteria securityCriteria);
    List<E> getAll(PagingDto pagingDto, SecurityCriteria securityCriteria);
    IVaselineDataScroller<E> scrollAll(SecurityCriteria securityCriteria);

    Long countByExample(D example, SecurityCriteria securityCriteria);
    List<E> searchByExample(D example, SecurityCriteria securityCriteria);
    List<E> searchByExample(D example, PagingDto pagingDto, SecurityCriteria securityCriteria);
    IVaselineDataScroller<E> scrollByExample(D example, SecurityCriteria securityCriteria);
}
