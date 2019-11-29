package ir.amv.os.vaseline.business.multidao.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseMultiDaoReadOnlyApi<Category, Id extends Serializable, E extends IBaseEntity<Id>>
        extends IBaseReadOnlyApi<Id, E> {

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getById(Object, Serializable)}
     */
    @Override
    default E getById(Id id) throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    E getById(Category category, Id id) throws BaseVaselineServerException;

    /**
     * {@inheritDoc}
     * @deprecated user {@link #countAll(Object)}
     */
    @Override
    default Long countAll() throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    Long countAll(Category category) throws BaseVaselineServerException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getAll(Object)}
     */
    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category) throws BaseVaselineServerException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getAll(Object, PagingDto)}
     */
    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #scrollAll(Object, List)}
     */
    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseVaselineServerException;
}
