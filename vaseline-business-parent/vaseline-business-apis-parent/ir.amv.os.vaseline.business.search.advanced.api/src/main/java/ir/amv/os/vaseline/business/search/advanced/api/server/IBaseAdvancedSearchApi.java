package ir.amv.os.vaseline.business.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchApi<I extends Serializable, E extends IBaseBusinessModel<I>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyApi<I, E> {

    Long countBySearchObject(SO searchObject) throws BaseBusinessException;
    List<E> searchBySearchObject(SO searchObject) throws BaseBusinessException;
    IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws BaseBusinessException;
    List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseBusinessException;
}
