package ir.amv.os.vaseline.data.apis.search.simple.server.criteria;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.criteria.IBaseCriteriaExampleParser;

import java.io.Serializable;

public interface IBaseCriteriaSimpleSearchParser<D extends IBaseDto<Id>, Id extends Serializable, CriteriaBuilder, Criterion, Projection>
        extends IBaseCriteriaExampleParser<D, IBaseDto, CriteriaBuilder, Criterion, Projection> {

}
