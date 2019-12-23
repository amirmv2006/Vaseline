package ir.amv.os.vaseline.service.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultReadOnlyService<Id extends
        Serializable, E extends IBaseBusinessModel<Id>, D extends IBaseDto<Id>, Api extends IBaseReadOnlyApi<Id, E>>
        extends IBaseReadOnlyService<Id, D>, IDefaultEntityReadOnlyService<Id, E, D, Api> {

    @Override
    default D getById(Id id) throws BaseExternalException {
        try {
            E byId = getApiProxy().getById(id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default Long countAll() throws BaseExternalException {
        try {
            return getApiProxy().countAll();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll() throws BaseExternalException {
        try {
            List<E> all = getApiProxy().getAll();
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseExternalException {
        try {
            List<E> sortedPaged = getApiProxy().getAll(pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
