package ir.amv.os.vaseline.data.apis.search.advanced.server.criteria;

import ir.amv.os.vaseline.data.apis.dao.server.criteria.IBaseCriteriaExampleParser;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;

public interface IBaseCriteriaAdvancedSearchParser<SO extends IBaseSearchObject, CriteriaBuilder, Criterion, Projection>
        extends IBaseCriteriaExampleParser<SO, CriteriaBuilder, Criterion, Projection> {

}
