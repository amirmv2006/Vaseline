package ir.amv.os.vaseline.ws.common.search.simple.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseSimpleSearchWebService<Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseReadOnlyWebService<Id, D> {

    Long countByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;
}
