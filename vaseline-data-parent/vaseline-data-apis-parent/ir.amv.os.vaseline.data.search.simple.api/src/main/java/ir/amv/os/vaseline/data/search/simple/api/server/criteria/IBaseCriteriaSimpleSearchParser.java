package ir.amv.os.vaseline.data.search.simple.api.server.criteria;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.criteria.IBaseCriteriaExampleParser;

import java.io.Serializable;

public interface IBaseCriteriaSimpleSearchParser<D extends IBaseDto<Id>, Id extends Serializable, CriteriaBuilder, Criterion, Projection>
        extends IBaseCriteriaExampleParser<D, IBaseDto, CriteriaBuilder, Criterion, Projection> {

}
