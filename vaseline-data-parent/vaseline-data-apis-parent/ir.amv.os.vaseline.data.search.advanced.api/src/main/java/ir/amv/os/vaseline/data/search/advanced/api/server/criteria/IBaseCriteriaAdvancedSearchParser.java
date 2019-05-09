package ir.amv.os.vaseline.data.search.advanced.api.server.criteria;

import ir.amv.os.vaseline.data.dao.basic.api.server.criteria.IBaseCriteriaExampleParser;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;

import java.util.ArrayList;
import java.util.List;

public interface IBaseCriteriaAdvancedSearchParser<SO extends IBaseSearchObject, CriteriaBuilder, Criterion, Projection>
        extends IBaseCriteriaExampleParser<IBaseSearchObject, SO, CriteriaBuilder, Criterion, Projection> {

    @Override
    default List<Class<?>> propertyClassesToBeIntercepted() {
        List<Class<?>> classes = new ArrayList<>(); // don't get from parent, it will cause problems
        // cause the generics for IBasePropertyCondition are primitives and #checkPropertyForClass will have problems
        classes.add(IBasePropertyCondition.class);
        return classes;
    }
}
