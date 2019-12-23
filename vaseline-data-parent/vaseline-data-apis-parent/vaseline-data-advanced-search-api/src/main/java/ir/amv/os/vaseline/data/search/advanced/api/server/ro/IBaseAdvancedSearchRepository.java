package ir.amv.os.vaseline.data.search.advanced.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.dao.basic.api.ro.IBasePersistentModelRepository;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 9/30/2017.
 */
public interface IBaseAdvancedSearchRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends IBasePersistentModelRepository<I, E> {

    Long countBySearchObject(S example);
    List<E> searchBySearchObject(S example);
    List<E> searchBySearchObject(S example, PagingDto pagingDto);
}
