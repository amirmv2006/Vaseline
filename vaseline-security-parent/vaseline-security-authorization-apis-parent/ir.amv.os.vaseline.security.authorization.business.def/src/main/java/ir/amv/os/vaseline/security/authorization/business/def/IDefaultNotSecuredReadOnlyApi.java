package ir.amv.os.vaseline.security.authorization.business.def;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.def.server.ro.IDefaultReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.security.authorization.business.api.IBaseNotSecuredReadOnlyApi;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultNotSecuredReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Dao extends
        IBaseReadOnlyDao<Id, E>>
        extends IDefaultReadOnlyApi<Id, E, Dao>,
        IBaseNotSecuredReadOnlyApi<E, Id> {

    @Override
    @Transactional
    default E getByIdNotSecured(Id id) throws BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.getById(id);
    }

    @Override
    @Transactional
    default Long countAllApproximatelyNotSecured() throws BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.countAllApproximately();
    }

    @Override
    @Transactional
    default Long countAllNotSecured() throws BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.countAll();
    }
    @Override
    @Transactional
    default List<E> getAllNotSecured() throws BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.getAll();
    }
    @Override
    @Transactional
    default IVaselineDataScroller<E> scrollAllNotSecured(final List<SortDto> sortList) throws
            BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.scrollAll(sortList);
    }
    @Override
    @Transactional
    default List<E> getAllNotSecured(PagingDto pagingDto) throws BaseVaselineServerException {
        return IDefaultReadOnlyApi.super.getAll(pagingDto);
    }
}
