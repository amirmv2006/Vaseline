package ir.amv.os.vaseline.business.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyApi<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(I id) throws BaseBusinessException;

    Long countAllApproximately() throws BaseBusinessException;

    Long countAll() throws BaseBusinessException;
    List<E> getAll() throws BaseBusinessException;
    IVaselineDataScroller<E> scrollAll(final List<SortDto> sortList) throws BaseBusinessException;
    List<E> getAll(PagingDto pagingDto) throws BaseBusinessException;

}
