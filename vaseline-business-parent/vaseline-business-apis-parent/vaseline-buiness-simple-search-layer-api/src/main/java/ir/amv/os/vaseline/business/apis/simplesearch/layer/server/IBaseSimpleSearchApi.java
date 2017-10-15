package ir.amv.os.vaseline.business.apis.simplesearch.layer.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;

import java.io.Serializable;
import java.util.List;

public interface IBaseSimpleSearchApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyApi<E, Id> {

    Long countByExample(D example) throws BaseVaselineServerException;
    List<E> searchByExample(D example) throws BaseVaselineServerException;
    IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) throws BaseVaselineServerException;
    List<E> searchByExample(D example, PagingDto pagingDto)
            throws BaseVaselineServerException;
}
