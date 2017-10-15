package ir.amv.os.vaseline.service.apis.layerimpl.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntitySearchValidation;
import ir.amv.os.vaseline.basics.apis.core.shared.validation.IEntityShowValidation;
import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.validation.server.exc.VaselineValidationServerException;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.apis.layer.server.ro.IBaseReadOnlyService;
import ir.amv.os.vaseline.service.apis.layerimpl.server.base.IBaseImplementedService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseImplementedReadOnlyService<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends
        Serializable>
        extends IBaseReadOnlyService<D, Id>, IBaseImplementedService {

    Class<E> getEntityClass();
    Class<D> getDtoClass();
    IBaseReadOnlyApi<E, Id> getReadApi();

    @Override
    default D getById(Id id) throws BaseVaselineClientException {
        try {
            E byId = getReadApi().getById(id);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default Long countAll() throws BaseVaselineClientException {
        try {
            return getReadApi().countAll();
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll() throws BaseVaselineClientException {
        try {
            List<E> all = getReadApi().getAll();
            return convertEntityToDTO(all, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    default List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> sortedPaged = getReadApi().getAll(pagingDto);
            return convertEntityToDTO(sortedPaged, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    // BASE METHODS
    default E convertDtoToEntity(D d, Class<?>... validationGroups) throws VaselineConvertException,
            VaselineValidationServerException {
        return convert(d, getEntityClass(), validationGroups);
    }

    default List<E> convertDtoToEntity(Collection<D> list, Class<?>... validationGroups) throws
            VaselineConvertException, VaselineValidationServerException {
        return convertList(list, getEntityClass(), validationGroups);
    }

    default D convertEntityToDTO(E e, Class<?>... validationGroups) throws VaselineConvertException, VaselineValidationServerException {
        return convert(e, getDtoClass(), validationGroups);
    }

    default List<D> convertEntityToDTO(Collection<E> list, Class<?>... validationGroups) throws VaselineConvertException,
            VaselineValidationServerException {
        return convertList(list, getDtoClass(), validationGroups);
    }

    default Class<?>[] validationGroupsForShow() {
        return new Class<?>[]{IEntityShowValidation.class};
    }

    default Class<?>[] validationGroupsForSearch() {
        return new Class<?>[]{IEntitySearchValidation.class};
    }


}
