package ir.amv.os.vaseline.service.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Api extends IBaseReadOnlyApi<E, Id>>
        extends IBaseReadOnlyService<D, Id>, IBaseImplementedEntityReadOnlyService<E, D, Id, Api> {

    @Override
    default D getById(Id id) throws BaseVaselineClientException {
        try {
            E byId = getApiProxy().getById(id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default Long countAll() throws BaseVaselineClientException {
        try {
            return getApiProxy().countAll();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll() throws BaseVaselineClientException {
        try {
            List<E> all = getApiProxy().getAll();
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> sortedPaged = getApiProxy().getAll(pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
