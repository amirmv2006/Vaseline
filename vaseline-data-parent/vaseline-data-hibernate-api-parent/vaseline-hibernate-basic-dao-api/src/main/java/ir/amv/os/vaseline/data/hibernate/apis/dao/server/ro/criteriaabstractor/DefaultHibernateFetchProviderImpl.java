package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

public class DefaultHibernateFetchProviderImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        implements IHibernateFetchProvider<E, Id> {
}
