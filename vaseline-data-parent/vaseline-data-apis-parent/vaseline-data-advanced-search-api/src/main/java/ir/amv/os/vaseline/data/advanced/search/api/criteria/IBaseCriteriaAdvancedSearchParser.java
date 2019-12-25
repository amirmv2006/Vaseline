package ir.amv.os.vaseline.data.advanced.search.api.criteria;

import ir.amv.os.vaseline.data.advanced.search.api.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.basic.api.criteria.IBaseCriteriaExampleParser;

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
