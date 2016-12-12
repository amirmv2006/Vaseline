package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBaseSearchObject;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.func.BaseApplyFunctionConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.func.IBaseApplyFunctionCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.logical.IMultiOperandLogicalCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.logical.ISingleOperandLogicalCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.logical.MultiOperandLogicalConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.logical.SingleOperandLogicalConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.multiop.IMultiOperandGeneralCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.multiop.MultiOperandGeneralConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.noop.INoOperandGeneralCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.noop.NoOperandGeneralConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop.*;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.twoop.ITwoOperandComparableCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.twoop.TwoOperandComparableConditionImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionInterceptor;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import javax.persistence.criteria.*;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by amv on 12/8/16.
 */
public class AdvancedSearchByExampleJPAProviderUtil {

    public static <T, SO> Predicate getRootCondition(final SO example, final CriteriaBuilder cb, String rootAlias, final Map<String, Path<?>> pathMap) {
        final MapList<String, Predicate> eachObject = new MapList<String, Predicate>();
        pathMap.get(""); // make sure from is there in the query
        ReflectionUtil.intercept(example, IBaseSearchObject.class, new ReflectionInterceptor<IBaseSearchObject>() {
            @Override
            public IBaseSearchObject intercept(IBaseSearchObject object, String propertyTreeName) {
                HashMap<String, Object> map = getMapFromObjectNonRecursive(object);
                Path propTNPath = pathMap.get(propertyTreeName);
                for (String key : map.keySet()) {
                    Expression<?> propExpr = propTNPath.get(key);
                    IBasePropertyCondition<?, ?> propValue = (IBasePropertyCondition<?, ?>) map.get(key);
                    Predicate expression = getExpression(propertyTreeName, propExpr, propValue, cb);
                    eachObject.add(propertyTreeName, expression);
                }
                return object;
            }
        }, rootAlias);
        List<Predicate> eachObjAnded = new ArrayList<Predicate>(eachObject.size());
        for (List<Predicate> predicates : eachObject.values()) {
            eachObjAnded.add(andAllPredicatesList(cb, predicates));
        }
        if (eachObjAnded.size() > 0) {
            return andAllPredicatesList(cb, eachObjAnded);
        }
        return null;
    }

    private static Predicate getExpression(String propertyTreeName, Expression<?> propExpr, IBasePropertyCondition<?, ?> propValue, CriteriaBuilder cb) {
        if (propValue instanceof IBaseApplyFunctionCondition<?>) {
            IBaseApplyFunctionCondition<?> baseApplyFunctionCondition = (IBaseApplyFunctionCondition<?>) propValue;
            BaseApplyFunctionConditionImpl.Operator operator = baseApplyFunctionCondition.getOperator();
            IBasePropertyCondition<?, ?> condition = baseApplyFunctionCondition.getCondition();
            switch (operator) {
                case lowerCase:
                    return getExpression(propertyTreeName, cb.lower((Expression<String>) propExpr), condition, cb);
                case upperCase:
                    return getExpression(propertyTreeName, cb.upper((Expression<String>) propExpr), condition, cb);
            }
        } else if (propValue instanceof INoOperandGeneralCondition<?>) {
            INoOperandGeneralCondition<?> noOpCond = (INoOperandGeneralCondition<?>) propValue;
            NoOperandGeneralConditionImpl.Operator operator = noOpCond.getOperator();
            switch (operator) {
                case alwaysFalse:
                    // http://stackoverflow.com/questions/14675229/jpa-criteria-api-how-to-express-literal-true-and-literal-false
                    return cb.or();
                case alwaysTrue:
                    return cb.and();
                case isNull:
                    return cb.isNull(propExpr);
                case isNotNull:
                    return cb.isNotNull(propExpr);
            }
        } else if (propValue instanceof IOneOperandGeneralCondition<?>) {
            IOneOperandGeneralCondition<?> oneOperandGeneralCondition = (IOneOperandGeneralCondition<?>) propValue;
            OneOperandGeneralConditionImpl.Operator operator = oneOperandGeneralCondition.getOperator();
            switch (operator) {
                case eq:
                    return cb.equal(propExpr, oneOperandGeneralCondition.getValue());
                case neq:
                    return cb.notEqual(propExpr, oneOperandGeneralCondition.getValue());
            }
        } else if (propValue instanceof IOneOperandComparableCondition<?>) {
            IOneOperandComparableCondition<?> oneOperandComparableCondition = (IOneOperandComparableCondition<?>) propValue;
            OneOperandComparableConditionImpl.Operator operator = oneOperandComparableCondition.getOperator();
            // FIXME: 12/9/16
            Comparable<?> value = (Comparable<?>) oneOperandComparableCondition.getValue();
            switch (operator) {
                case gt:
                    return cb.greaterThan((Expression<? extends Comparable>) propExpr, value);
                case lt:
                    return cb.lessThan((Expression<? extends Comparable>) propExpr, value);
                case gtEq:
                    return cb.greaterThanOrEqualTo((Expression<? extends Comparable>) propExpr, value);
                case ltEq:
                    cb.lessThanOrEqualTo((Expression<? extends Comparable>) propExpr, value);
            }
        } else if (propValue instanceof IOneOperandStringCondition) {
            IOneOperandStringCondition oneOperandStringCondition = (IOneOperandStringCondition) propValue;
            OneOperandStringConditionImpl.Operator operator = oneOperandStringCondition.getOperator();
            switch (operator) {
                case contains:
                    return cb.like((Expression<String>) propExpr, '%' + oneOperandStringCondition.getValue() + '%');
                case endswith:
                    return cb.like((Expression<String>) propExpr, '%' + oneOperandStringCondition.getValue());
                case startswith:
                    return cb.like((Expression<String>) propExpr, oneOperandStringCondition.getValue() + '%');
            }
        } else if (propValue instanceof ITwoOperandComparableCondition<?>) {
            ITwoOperandComparableCondition<?> twoOperandComparableCondition = (ITwoOperandComparableCondition<?>) propValue;
            TwoOperandComparableConditionImpl.Operator operator = twoOperandComparableCondition.getOperator();
            Expression<? extends Comparable<?>> propExprCasted = (Expression<? extends Comparable<?>>) propExpr;
            Comparable<?> lowerValueInclusive = (Comparable<?>) twoOperandComparableCondition.getLowerValueInclusive();
            Comparable<?> higherValueExclusive = (Comparable<?>) twoOperandComparableCondition.getHigherValueExclusive();
            switch (operator) {
                case between:
                    return cb.between((Expression<? extends Comparable>) propExprCasted, lowerValueInclusive, higherValueExclusive);
            }
        } else if (propValue instanceof IMultiOperandGeneralCondition<?>) {
            IMultiOperandGeneralCondition<?> multiOperandGeneralCondition = (IMultiOperandGeneralCondition<?>) propValue;
            MultiOperandGeneralConditionImpl.Operator operator = multiOperandGeneralCondition.getOperator();
            switch (operator) {
                case in:
                    Collection valueCollection = multiOperandGeneralCondition.getValueCollection();
                    return cb.isTrue(propExpr.in(valueCollection));
            }
        } else if (propValue instanceof ISingleOperandLogicalCondition<?>) {
            ISingleOperandLogicalCondition<?> singleOperandLogicalCondition = (ISingleOperandLogicalCondition<?>) propValue;
            SingleOperandLogicalConditionImpl.Operator operator = singleOperandLogicalCondition.getOperator();
            switch (operator) {
                case not:
                    IBasePropertyCondition<?, ?> operand = singleOperandLogicalCondition.getOperand();
                    Predicate operandExpr = getExpression(propertyTreeName, propExpr, propValue, cb);
                    return cb.not(operandExpr);
            }
        } else if (propValue instanceof IMultiOperandLogicalCondition<?>) {
            IMultiOperandLogicalCondition<?> multiOperandLogicalCondition = (IMultiOperandLogicalCondition<?>) propValue;
            MultiOperandLogicalConditionImpl.Operator operator = multiOperandLogicalCondition.getOperator();
            Collection<? extends IBasePropertyCondition<?, ?>> operandCollection = multiOperandLogicalCondition.getOperandCollection();
            Collection<Predicate> predicates = new ArrayList<Predicate>();
            for (IBasePropertyCondition<?, ?> basePropertyCondition : operandCollection) {
                Predicate expression = getExpression(propertyTreeName, propExpr, basePropertyCondition, cb);
                predicates.add(expression);
            }
            switch (operator) {
                case and:
                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));
                case or:
                    return cb.or(predicates.toArray(new Predicate[predicates.size()]));
            }
        }
        return null;
    }

    private static Predicate andAllPredicatesList(CriteriaBuilder cb, List<Predicate> predicates) {
        return predicates.size() == 1 ? predicates.get(0) : cb.and(predicates.toArray(new Predicate[predicates.size()]));
    }

    private static HashMap<String, Object> getMapFromObjectNonRecursive(Object example) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(example.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                checkPropertyForClass(example, result, propertyDescriptor, IBasePropertyCondition.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * searches a property descriptor for query class and puts its value on the
     * result map if found
     *
     * @param example
     * @param result
     * @param propertyDescriptor
     * @param query
     */
    private static void checkPropertyForClass(Object example, HashMap<String, Object> result, PropertyDescriptor propertyDescriptor, Class<?> query) {
        if (propertyDescriptor.getWriteMethod() == null) {
            return;
        }
        Method readMethod = propertyDescriptor.getReadMethod();
        //FIXME getId method
        if (readMethod.getReturnType().equals(Serializable.class)) {
            try {
                Object invoke = readMethod.invoke(example);
                if (invoke != null) {
                    result.put(propertyDescriptor.getName(), invoke);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (readMethod.isBridge()) {
            try {
                readMethod = example.getClass().getMethod(readMethod.getName(), readMethod.getParameterTypes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Class<?> searchResult = ReflectionUtil.searchMethodReturnType(readMethod, query);
        if (searchResult != null) {
            Object value = ReflectionUtil.getMethodResultAsArray(example, readMethod, query)[0];
            if (value != null) {
                result.put(propertyDescriptor.getName(), value);
            }
        }
        searchResult = ReflectionUtil.searchMethodReturnTypeForEnum(readMethod);
        if (searchResult != null) {
            Object value = ReflectionUtil.getMethodResultAsArray(example, readMethod, searchResult)[0];
            if (value != null) {
                result.put(propertyDescriptor.getName(), value);
            }
        }
    }


    private static class MapList<K, V> extends HashMap<K, List<V>> {
        public void add(K key, V value) {
            List<V> list = get(key);
            if (list == null) {
                list = new ArrayList<V>();
                put(key, list);
            }
            list.add(value);
        }
    }
}
