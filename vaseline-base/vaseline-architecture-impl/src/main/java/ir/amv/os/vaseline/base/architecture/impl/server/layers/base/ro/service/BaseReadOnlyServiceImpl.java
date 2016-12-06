package ir.amv.os.vaseline.base.architecture.impl.server.layers.base.ro.service;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.service.BaseServiceImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service.IBaseReadOnlyService;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.base.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyServiceImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, API extends IBaseReadOnlyApi<E, D, Id>>
        extends BaseServiceImpl
        implements IBaseReadOnlyService<D, Id> {

    protected API api;
    protected Class<E> entityClass;
    protected Class<D> dtoClass;

    public BaseReadOnlyServiceImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>) genericArgumentClasses[0]);
            setDtoClass((Class<D>) genericArgumentClasses[1]);
        }
    }

    @Override
    public D getById(Id id) throws BaseVaselineClientException {
        try {
            E byId = api.getById(id);
            D result = convertEntityToDTO(byId);
            return result;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public Long countAll() throws BaseVaselineClientException {
        try {
            Long count = api.countAll();
            return count;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public List<D> getAll() throws BaseVaselineClientException {
        try {
            List<E> all = api.getAll();
            List<D> result = convertEntityToDTO(all);
            return result;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> sortedPaged = api.getAll(pagingDto);
            List<D> result = convertEntityToDTO(sortedPaged);
            return result;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public Long countByExample(D example) throws BaseVaselineClientException {
        try {
            Long countByExample = api.countByExample(example);
            return countByExample;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public List<D> searchByExample(D example) throws BaseVaselineClientException {
        try {
            List<E> searchByExample = api.searchByExample(example);
            List<D> result = convertEntityToDTO(searchByExample);
            return result;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    @Override
    public List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException {
        try {
            List<E> searchByExample = api.searchByExample(example, pagingDto);
            List<D> result = convertEntityToDTO(searchByExample);
            return result;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    // BASE METHODS
    public E convertDtoToEntity(D d) throws VaselineConvertException {
        return convert(d, entityClass);
    }

    public List<E> convertDtoToEntity(Collection<D> list) throws VaselineConvertException {
        return convertList(list, entityClass);
    }

    public D convertEntityToDTO(E e) throws VaselineConvertException {
        return convert(e, dtoClass);
    }

    public List<D> convertEntityToDTO(Collection<E> list) throws VaselineConvertException {
        return convertList(list, dtoClass);
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void setDtoClass(Class<D> dtoClass) {
        this.dtoClass = dtoClass;
    }

    // Spring Dependencies
    @Autowired
    public void setApi(API api) {
        this.api = api;
    }

}
