package ir.amv.os.vaseline.service.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedEntityReadOnlyService;
import ir.amv.os.vaseline.service.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category, Api extends IBaseMultiDaoReadOnlyApi<E, Id, Category>>
        extends IBaseMultiDaoReadOnlyService<D, Id, Category>, IBaseImplementedEntityReadOnlyService<E, D, Id, Api> {

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
