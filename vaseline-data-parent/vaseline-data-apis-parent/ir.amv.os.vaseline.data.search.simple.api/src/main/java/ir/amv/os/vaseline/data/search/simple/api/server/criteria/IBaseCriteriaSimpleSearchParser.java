package ir.amv.os.vaseline.data.search.simple.api.server.criteria;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.criteria.IBaseCriteriaExampleParser;

import java.io.Serializable;

public interface IBaseCriteriaSimpleSearchParser<Id extends Serializable, D extends IBaseDto<Id>, CriteriaBuilder, Criterion, Projection>
        extends IBaseCriteriaExampleParser<IBaseDto, D, CriteriaBuilder, Criterion, Projection> {

}
