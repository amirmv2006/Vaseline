package ir.amv.os.vaseline.service.apis.simplesearch.layer.server;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.layer.server.ro.IBaseReadOnlyService;

import java.io.Serializable;
import java.util.List;

public interface IBaseSimpleSearchService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyService<D, Id> {

    Long countByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;
}
