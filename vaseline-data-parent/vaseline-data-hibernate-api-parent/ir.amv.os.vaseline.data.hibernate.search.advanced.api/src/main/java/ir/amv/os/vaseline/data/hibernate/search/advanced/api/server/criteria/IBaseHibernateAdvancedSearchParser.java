package ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.criteria;

import ir.amv.os.vaseline.data.dao.basic.api.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.search.advanced.api.server.criteria.IBaseCriteriaAdvancedSearchParser;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.func.BaseApplyFunctionConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.func.IBaseApplyFunctionCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical.IMultiOperandLogicalCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical.ISingleOperandLogicalCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical.MultiOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical.SingleOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.multiop.IMultiOperandGeneralCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.multiop.MultiOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.noop.INoOperandGeneralCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.noop.NoOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.IOneOperandComparableCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.IOneOperandGeneralCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.IOneOperandStringCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.OneOperandComparableConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.OneOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.oneop.OneOperandStringConditionImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.twoop.ITwoOperandComparableCondition;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.twoop.TwoOperandComparableConditionImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.hibernatepatch.StringFunctionCriterion;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionInterruptException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface IBaseHibernateAdvancedSearchParser<SO extends IBaseSearchObject>
        extends IBaseCriteriaAdvancedSearchParser<SO, DetachedCriteria, Criterion, String> {

    @Override
    default SearchJoinType getJoinTypeFromExample(IBaseSearchObject object) {
        return object.getJoinType();
    }

    @Override
    default void removeUnnecessaryKeysFromObjectMap(HashMap<String, Object> map) {
        map.remove("joinType");
    }

    @Override
    default Criterion getPropertyCriterion(String propertyAlias, Object propertyValue, DetachedCriteria criteriaBuilder) throws InterceptionInterruptException {
        if (propertyValue instanceof IBaseApplyFunctionCondition<?>) {
            IBaseApplyFunctionCondition<?> baseApplyFunctionCondition = (IBaseApplyFunctionCondition<?>) propertyValue;
            BaseApplyFunctionConditionImpl.Operator operator = baseApplyFunctionCondition.getOperator();
            IBasePropertyCondition<?, ?> condition = baseApplyFunctionCondition.getCondition();
            Criterion innerCiterion = getPropertyCriterion(propertyAlias, condition, criteriaBuilder);
            switch (operator) {
                case lowerCase:
                    return new StringFunctionCriterion(propertyAlias, StringFunctionCriterion.StringFunction.LOWER, innerCiterion);
                case upperCase:
                    return new StringFunctionCriterion(propertyAlias, StringFunctionCriterion.StringFunction.UPPER, innerCiterion);
            }
        } else if (propertyValue instanceof INoOperandGeneralCondition<?>) {
            INoOperandGeneralCondition<?> noOpCond = (INoOperandGeneralCondition<?>) propertyValue;
            NoOperandGeneralConditionImpl.Operator operator = noOpCond.getOperator();
            switch (operator) {
                case alwaysFalse:
                    // http://stackoverflow.com/questions/14675229/jpa-criteria-api-how-to-express-literal-true-and-literal-false
                    return Restrictions.sqlRestriction("1=0");
                case alwaysTrue:
                    return Restrictions.conjunction();
                case isNull:
                    return Restrictions.isNull(propertyAlias);
                case isNotNull:
                    return Restrictions.isNotNull(propertyAlias);
            }
        } else if (propertyValue instanceof IOneOperandGeneralCondition<?>) {
            IOneOperandGeneralCondition<?> oneOperandGeneralCondition = (IOneOperandGeneralCondition<?>) propertyValue;
            OneOperandGeneralConditionImpl.Operator operator = oneOperandGeneralCondition.getOperator();
            switch (operator) {
                case eq:
                    return Restrictions.eq(propertyAlias, oneOperandGeneralCondition.getValue());
                case neq:
                    return Restrictions.ne(propertyAlias, oneOperandGeneralCondition.getValue());
            }
        } else if (propertyValue instanceof IOneOperandComparableCondition<?>) {
            IOneOperandComparableCondition<?> oneOperandComparableCondition = (IOneOperandComparableCondition<?>) propertyValue;
            OneOperandComparableConditionImpl.Operator operator = oneOperandComparableCondition.getOperator();
            // FIXME: 12/9/16
            Comparable value = (Comparable) oneOperandComparableCondition.getValue();
            switch (operator) {
                case gt:
                    return Restrictions.gt(propertyAlias, value);
                case lt:
                    return Restrictions.lt(propertyAlias, value);
                case gtEq:
                    return Restrictions.ge(propertyAlias, value);
                case ltEq:
                    return Restrictions.le(propertyAlias, value);
            }
        } else if (propertyValue instanceof IOneOperandStringCondition) {
            IOneOperandStringCondition oneOperandStringCondition = (IOneOperandStringCondition) propertyValue;
            OneOperandStringConditionImpl.Operator operator = oneOperandStringCondition.getOperator();
            switch (operator) {
                case contains:
                    return Restrictions.like(propertyAlias, oneOperandStringCondition.getValue(), MatchMode.ANYWHERE);
                case endswith:
                    return Restrictions.like(propertyAlias, oneOperandStringCondition.getValue(), MatchMode.END);
                case startswith:
                    return Restrictions.like(propertyAlias, oneOperandStringCondition.getValue(), MatchMode.START);
            }
        } else if (propertyValue instanceof ITwoOperandComparableCondition<?>) {
            ITwoOperandComparableCondition<?> twoOperandComparableCondition = (ITwoOperandComparableCondition<?>) propertyValue;
            TwoOperandComparableConditionImpl.Operator operator = twoOperandComparableCondition.getOperator();
            Comparable lowerValueInclusive = (Comparable<?>) twoOperandComparableCondition.getLowerValueInclusive();
            Comparable higherValueExclusive = (Comparable<?>) twoOperandComparableCondition.getHigherValueExclusive();
            switch (operator) {
                case between:
                    return Restrictions.between(propertyAlias, lowerValueInclusive, higherValueExclusive);
            }
        } else if (propertyValue instanceof IMultiOperandGeneralCondition<?>) {
            IMultiOperandGeneralCondition<?> multiOperandGeneralCondition = (IMultiOperandGeneralCondition<?>) propertyValue;
            MultiOperandGeneralConditionImpl.Operator operator = multiOperandGeneralCondition.getOperator();
            switch (operator) {
                case in:
                    Collection valueCollection = multiOperandGeneralCondition.getValueCollection();
                    return Restrictions.in(propertyAlias, valueCollection);
            }
        } else if (propertyValue instanceof ISingleOperandLogicalCondition<?>) {
            ISingleOperandLogicalCondition<?> singleOperandLogicalCondition = (ISingleOperandLogicalCondition<?>) propertyValue;
            SingleOperandLogicalConditionImpl.Operator operator = singleOperandLogicalCondition.getOperator();
            switch (operator) {
                case not:
                    IBasePropertyCondition<?, ?> operand = singleOperandLogicalCondition.getOperand();
                    Criterion operandExpr = getPropertyCriterion(propertyAlias, operand, criteriaBuilder);
                    return Restrictions.not(operandExpr);
            }
        } else if (propertyValue instanceof IMultiOperandLogicalCondition<?>) {
            IMultiOperandLogicalCondition<?> multiOperandLogicalCondition = (IMultiOperandLogicalCondition<?>) propertyValue;
            MultiOperandLogicalConditionImpl.Operator operator = multiOperandLogicalCondition.getOperator();
            Collection<? extends IBasePropertyCondition<?, ?>> operandCollection = multiOperandLogicalCondition.getOperandCollection();
            Collection<Criterion> predicates = new ArrayList<>();
            for (IBasePropertyCondition<?, ?> basePropertyCondition : operandCollection) {
                Criterion expression = getPropertyCriterion(propertyAlias, basePropertyCondition, criteriaBuilder);
                predicates.add(expression);
            }
            switch (operator) {
                case and:
                    return Restrictions.and(predicates.toArray(new Criterion[predicates.size()]));
                case or:
                    return Restrictions.or(predicates.toArray(new Criterion[predicates.size()]));
            }
        }
        return null;
    }

    @Override
    default Criterion andAll(DetachedCriteria criteriaBuilder, List<Criterion> criterionList) {
        return Restrictions.and(criterionList.toArray(new Criterion[0]));
    }

    @Override
    default Criterion orAll(DetachedCriteria criteriaBuilder, List<Criterion> criterionList) {
        return Restrictions.or(criterionList.toArray(new Criterion[0]));
    }

}
