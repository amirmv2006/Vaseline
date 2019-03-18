package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;

import java.io.Serializable;

public class DefaultJpaFetchProviderImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        implements IJpaFetchProvider<E, Id> {
}
