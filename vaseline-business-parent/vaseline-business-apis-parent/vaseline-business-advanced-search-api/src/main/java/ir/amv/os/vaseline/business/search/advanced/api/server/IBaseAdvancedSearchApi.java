package ir.amv.os.vaseline.business.search.advanced.api.server;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessEntity;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface IBaseAdvancedSearchApi<I extends Serializable, M extends IBaseBusinessEntity<I, M>, S extends IBaseSearchObject>
        extends IBaseReadOnlyApi<I, M> {

    Long countBySearchObject(S searchObject) throws BaseBusinessException;
    List<M> searchBySearchObject(S searchObject) throws BaseBusinessException;
    Page<M> searchBySearchObject(S searchObject, Pageable pagingDto)
            throws BaseBusinessException;
}
