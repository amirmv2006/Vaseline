package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;

import java.io.Serializable;

public class DefaultHibernateFetchProviderImpl<I extends Serializable, E extends IBaseEntity<I>, D extends IBaseDto<I>>
        implements IHibernateFetchProvider<I, E> {
}
