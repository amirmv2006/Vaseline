package ir.amv.os.vaseline.business.multidao.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseMultiDaoReadOnlyApi<Category, Id extends Serializable, E extends IBaseBusinessModel<Id>>
        extends IBaseReadOnlyApi<Id, E> {

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getById(Object, Serializable)}
     */
    @Override
    default E getById(Id id) throws BaseBusinessException {
        throw new VaselineFeatureNotSupportedException();
    }
    E getById(Category category, Id id) throws BaseBusinessException;

    /**
     * {@inheritDoc}
     * @deprecated user {@link #countAll(Object)}
     */
    @Override
    default Long countAll() throws BaseBusinessException {
        throw new VaselineFeatureNotSupportedException();
    }
    Long countAll(Category category) throws BaseBusinessException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getAll(Object)}
     */
    @Override
    default List<E> getAll() throws BaseBusinessException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category) throws BaseBusinessException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #getAll(Object, PagingDto)}
     */
    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseBusinessException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category, PagingDto pagingDto) throws BaseBusinessException;

    /**
     * {@inheritDoc}
     * @deprecated use {@link #scrollAll(Object, List)}
     */
    @Override
    default IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseBusinessException {
        throw new VaselineFeatureNotSupportedException();
    }
    IVaselineDataScroller<E> scrollAll(Category category, List<SortDto> sortList) throws BaseBusinessException;
}
