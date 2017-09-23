package ir.amv.os.vaseline.security.authorization.api.shared.base.dao.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/25/2016.
 */
public interface IBaseSecuredReadOnlyDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends ISecurityCriteria>
        extends IBaseReadOnlyDao<E, D, Id> {

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
