package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;

import java.io.Serializable;

public class DefaultJpaFetchProviderImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        implements IJpaFetchProvider<E, Id> {
}
