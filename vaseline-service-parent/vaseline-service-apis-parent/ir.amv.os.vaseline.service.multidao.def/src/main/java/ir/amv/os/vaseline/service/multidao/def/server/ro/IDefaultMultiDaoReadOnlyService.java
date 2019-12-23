package ir.amv.os.vaseline.service.multidao.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.business.multidao.api.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultEntityReadOnlyService;
import ir.amv.os.vaseline.service.multidao.api.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IDefaultMultiDaoReadOnlyService<Category, Id extends
        Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, Api extends IBaseMultiDaoReadOnlyApi<Category, Id, E>>
        extends IBaseMultiDaoReadOnlyService<Category, Id, D>, IDefaultEntityReadOnlyService<Id, E, D, Api> {

    @Override
    default D getById(Category category, Id id) throws BaseExternalException {
        try {
            E byId = getApiProxy().getById(category, id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default Long countAll(Category category) throws BaseExternalException {
        try {
            return getApiProxy().countAll(category);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category) throws BaseExternalException {
        try {
            List<E> all = getApiProxy().getAll(category);
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category, PagingDto pagingDto) throws BaseExternalException {
        try {
            List<E> sortedPaged = getApiProxy().getAll(category, pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
