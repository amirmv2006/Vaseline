package ir.amv.os.vaseline.business.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchApi<I extends Serializable, M extends IBaseBusinessModel<I>, S extends IBaseSearchObject>
        extends IBaseReadOnlyApi<I, M> {

    Long countBySearchObject(S searchObject) throws BaseBusinessException;
    List<M> searchBySearchObject(S searchObject) throws BaseBusinessException;
    List<M> searchBySearchObject(S searchObject, PagingDto pagingDto)
            throws BaseBusinessException;
}
