package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;

import java.io.Serializable;

public class DefaultHibernateFetchProviderImpl<I extends Serializable, E extends IBaseBusinessModel<I>, D extends IBaseDto<I>>
        implements IHibernateFetchProvider<I, E> {
}
