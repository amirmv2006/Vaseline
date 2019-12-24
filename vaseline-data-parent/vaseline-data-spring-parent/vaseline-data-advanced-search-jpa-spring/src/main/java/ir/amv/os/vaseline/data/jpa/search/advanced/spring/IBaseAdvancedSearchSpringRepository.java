package ir.amv.os.vaseline.data.jpa.search.advanced.spring;

import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;
import ir.amv.os.vaseline.data.spring.common.repo.SpringRepository;

import java.io.Serializable;

public interface IBaseAdvancedSearchSpringRepository<I extends Serializable, E extends IBasePersistenceModel<I>, S extends IBaseSearchObject>
        extends SpringRepository<I, E>, IBaseAdvancedSearchRepository<I, E, S> {
}
