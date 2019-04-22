package ir.amv.os.vaseline.ws.common.search.simple.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.common.basic.api.ro.IBaseReadOnlyWebService;

import java.io.Serializable;
import java.util.List;

/**
 * @author Amir
 */
public interface IBaseSimpleSearchWebService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseReadOnlyWebService<D, Id> {

    Long countByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;
}
