package ir.amv.os.vaseline.service.apis.multidao.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedEntityReadOnlyService;
import ir.amv.os.vaseline.service.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedMultiDaoReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable, Category>
        extends IBaseMultiDaoReadOnlyService<D, Id, Category>, IBaseImplementedEntityReadOnlyService<E, D, Id> {
    IBaseMultiDaoReadOnlyApi<E, Id, Category> getReadApi();

    @Override
    default D getById(Category category, Id id) throws BaseVaselineClientException {
        try {
            E byId = getReadApi().getById(category, id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default Long countAll(Category category) throws BaseVaselineClientException {
        try {
            return getReadApi().countAll(category);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category) throws BaseVaselineClientException {
        try {
            List<E> all = getReadApi().getAll(category);
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    default List<D> getAll(Category category, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> sortedPaged = getReadApi().getAll(category, pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
