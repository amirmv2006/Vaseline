package ir.amv.os.vaseline.business.apis.advancedsearch.layer.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.basic.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchApi<E extends IBaseEntity<Id>, SO extends IBaseSearchObject, Id extends Serializable>
        extends IBaseReadOnlyApi<E, Id> {

    Long countBySearchObject(SO searchObject) throws BaseVaselineServerException;
    List<E> searchBySearchObject(SO searchObject) throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollBySearchObject(SO searchObject, List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> searchBySearchObject(SO searchObject, PagingDto pagingDto)
            throws BaseVaselineServerException;
}
