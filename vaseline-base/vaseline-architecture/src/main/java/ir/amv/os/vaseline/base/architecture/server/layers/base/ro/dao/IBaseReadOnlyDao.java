package ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.architecture.server.layers.parent.dao.IBaseDao;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseDao {

    E getById(Id id);
    E getByIdDetached(Id id);

    Long countAllApproximately();

    Long countAll();
    List<E> getAll();
    List<E> getAll(PagingDto pagingDto);
    IVaselineDataScroller<E> scrollAll();

    Long countByExample(D example);
    List<E> searchByExample(D example);
    List<E> searchByExample(D example, PagingDto pagingDto);
    IVaselineDataScroller<E> scrollByExample(D example);

    void setEntityClass(Class<E> entityClass);
    Class<E> getEntityClass();

}
