package ir.amv.os.vaseline.service.multidao.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.multidao.api.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultEntityReadOnlyService;
import ir.amv.os.vaseline.service.multidao.api.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Api extends IBaseMultiDaoReadOnlyApi<E, Id, Category>>
        extends IBaseMultiDaoReadOnlyService<D, Id, Category>, IDefaultEntityReadOnlyService<E, D, Id, Api> {

    @Override
    default D getById(Category category, Id id) throws BaseVaselineClientException {
        try {
            E byId = getApiProxy().getById(category, id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default Long countAll(Category category) throws BaseVaselineClientException {
        try {
            return getApiProxy().countAll(category);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category) throws BaseVaselineClientException {
        try {
            List<E> all = getApiProxy().getAll(category);
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> sortedPaged = getApiProxy().getAll(category, pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
