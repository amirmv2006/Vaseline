package ir.amv.os.vaseline.business.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.basic.api.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchApi<I extends Serializable, E extends IBaseEntity<I>, SO extends IBaseSearchObject>
        extends IBaseReadOnlyApi<I, E> {

    Long countBySearchObject(SO searchObject) throws BaseVaselineServerException;
    List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException;
}
