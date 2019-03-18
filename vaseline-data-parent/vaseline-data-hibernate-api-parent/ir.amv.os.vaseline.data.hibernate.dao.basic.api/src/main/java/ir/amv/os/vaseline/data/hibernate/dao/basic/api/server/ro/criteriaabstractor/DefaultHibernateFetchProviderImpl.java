package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

public class DefaultHibernateFetchProviderImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        implements IHibernateFetchProvider<E, Id> {
}
