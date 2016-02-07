package ir.amv.os.vaseline.base.architecture.impl.server.layers.ro.api;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.api.BaseApiImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.ro.api.IBaseReadOnlyApi;
import ir.amv.os.vaseline.base.architecture.server.layers.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/8/2016.
 */
public class BaseReadOnlyApiImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, DAO extends IBaseReadOnlyDao<E, D, Id>>
        extends BaseApiImpl
        implements IBaseReadOnlyApi<E, D, Id> {

    protected DAO dao;

    protected Class<E> entityClass;

    public BaseReadOnlyApiImpl() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass());
        if (genericArgumentClasses != null) {
            setEntityClass((Class<E>) genericArgumentClasses[0]);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public E getById(Id id) throws BaseVaselineServerException {
        E findById = dao.getById(id);
        postGet(findById);
        return findById;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countAll() throws BaseVaselineServerException {
        Long countAll = dao.countAll();
        return countAll;
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll() throws BaseVaselineServerException {
        List<E> all = dao.getAll();
        if (all != null) {
            for (E entity : all) {
                postGet(entity);
            }
        }
        return all;
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> all = dao.getAll(pagingDto);
        if (all != null) {
            for (E entity : all) {
                postGet(entity);
            }
        }
        return all;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countByExample(D example) throws BaseVaselineServerException {
        Long countByExample = dao.countByExample(example);
        return countByExample;
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example) throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }

    @Override
    @Transactional(readOnly = true)
    public List<E> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineServerException {
        List<E> searchByExample = dao.searchByExample(example, pagingDto);
        if (searchByExample != null) {
            for (E entity : searchByExample) {
                postGet(entity);
            }
        }
        return searchByExample;
    }

    @Override
    public void postGet(E entity) throws BaseVaselineServerException {
    }

    public void setEntityClass(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    public void setDao(DAO dao) {
        this.dao = dao;
    }
}
