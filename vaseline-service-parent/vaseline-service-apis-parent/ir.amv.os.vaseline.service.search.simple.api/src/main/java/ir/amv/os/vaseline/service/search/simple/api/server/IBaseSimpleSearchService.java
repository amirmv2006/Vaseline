package ir.amv.os.vaseline.service.search.simple.api.server;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseSimpleSearchService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseReadOnlyService<Id, D> {

    Long countByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;
}
