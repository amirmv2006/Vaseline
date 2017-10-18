package ir.amv.os.vaseline.data.apis.dao.basic.server.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.dao.basic.server.base.IBaseDao;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyDao<E extends IBaseEntity<Id>, Id extends Serializable>
        extends IBaseDao {

    E getById(Id id);
    E getByIdDetached(Id id);

    Long countAllApproximately();

    Long countAll();
    List<E> getAll();
    List<E> getAll(PagingDto pagingDto);
    IVaselineDataScroller<E> scrollAll(List<SortDto> sortList);

    // dev note: don't implement these in parent classes, otherwise will be coupled with IBaseEntity
    void setEntityClass(Class<E> entityClass);
    Class<E> getEntityClass();

}
