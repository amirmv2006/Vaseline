package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.criteria;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.apis.dao.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.apis.search.advanced.server.criteria.IBaseCriteriaAdvancedSearchParser;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.func.BaseApplyFunctionConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.func.IBaseApplyFunctionCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.logical.IMultiOperandLogicalCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.logical.ISingleOperandLogicalCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.logical.MultiOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.logical.SingleOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.multiop.IMultiOperandGeneralCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.multiop.MultiOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.noop.INoOperandGeneralCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.noop.NoOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.IOneOperandComparableCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.IOneOperandGeneralCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.IOneOperandStringCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.OneOperandComparableConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.OneOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop.OneOperandStringConditionImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.twoop.ITwoOperandComparableCondition;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.twoop.TwoOperandComparableConditionImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionInterruptException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public interface IBaseJpaAdvancedSearchParser<SO extends IBaseSearchObject>
        extends IBaseCriteriaAdvancedSearchParser<SO, CriteriaBuilder, Predicate, Path> {

    @Override
    default SearchJoinType getJoinTypeFromExample(IBaseSearchObject object) {
        return object.getJoinType();
    }

    @Override
    default void removeUnnecessaryKeysFromObjectMap(HashMap<String, Object> map) {
        map.remove("joinType");
    }

    @Override
    default Predicate getPropertyCriterion(
            Path propertyAlias, Object propertyValue,
            CriteriaBuilder criteriaBuilder
    ) throws InterceptionInterruptException {
        if (propertyValue instanceof IBaseApplyFunctionCondition<?>) {
            IBaseApplyFunctionCondition<?> baseApplyFunctionCondition = (IBaseApplyFunctionCondition<?>) propertyValue;
            BaseApplyFunctionConditionImpl.Operator operator = baseApplyFunctionCondition.getOperator();
            IBasePropertyCondition<?, ?> condition = baseApplyFunctionCondition.getCondition();
            switch (operator) {
                case lowerCase:
                    return getPropertyCriterion((From) criteriaBuilder.lower(propertyAlias), condition, criteriaBuilder);
                case upperCase:
                    return getPropertyCriterion((From) criteriaBuilder.upper(propertyAlias), condition, criteriaBuilder);
            }
        } else if (propertyValue instanceof INoOperandGeneralCondition<?>) {
            INoOperandGeneralCondition<?> noOpCond = (INoOperandGeneralCondition<?>) propertyValue;
            NoOperandGeneralConditionImpl.Operator operator = noOpCond.getOperator();
            switch (operator) {
                case alwaysFalse:
                    // http://stackoverflow.com/questions/14675229/jpa-criteria-api-how-to-express-literal-true-and-literal-false
                    return criteriaBuilder.or();
                case alwaysTrue:
                    return criteriaBuilder.and();
                case isNull:
                    return criteriaBuilder.isNull(propertyAlias);
                case isNotNull:
                    return criteriaBuilder.isNotNull(propertyAlias);
            }
        } else if (propertyValue instanceof IOneOperandGeneralCondition<?>) {
            IOneOperandGeneralCondition<?> oneOperandGeneralCondition = (IOneOperandGeneralCondition<?>) propertyValue;
            OneOperandGeneralConditionImpl.Operator operator = oneOperandGeneralCondition.getOperator();
            switch (operator) {
                case eq:
                    return criteriaBuilder.equal(propertyAlias, oneOperandGeneralCondition.getValue());
                case neq:
                    return criteriaBuilder.notEqual(propertyAlias, oneOperandGeneralCondition.getValue());
            }
        } else if (propertyValue instanceof IOneOperandComparableCondition<?>) {
            IOneOperandComparableCondition<?> oneOperandComparableCondition = (IOneOperandComparableCondition<?>) propertyValue;
            OneOperandComparableConditionImpl.Operator operator = oneOperandComparableCondition.getOperator();
            // FIXME: 12/9/16
            Comparable value = (Comparable) oneOperandComparableCondition.getValue();
            switch (operator) {
                case gt:
                    return criteriaBuilder.greaterThan(propertyAlias, value);
                case lt:
                    return criteriaBuilder.lessThan(propertyAlias, value);
                case gtEq:
                    return criteriaBuilder.greaterThanOrEqualTo(propertyAlias, value);
                case ltEq:
                    return criteriaBuilder.lessThanOrEqualTo(propertyAlias, value);
            }
        } else if (propertyValue instanceof IOneOperandStringCondition) {
            IOneOperandStringCondition oneOperandStringCondition = (IOneOperandStringCondition) propertyValue;
            OneOperandStringConditionImpl.Operator operator = oneOperandStringCondition.getOperator();
            switch (operator) {
                case contains:
                    return criteriaBuilder.like(propertyAlias, '%' + oneOperandStringCondition.getValue() + '%');
                case endswith:
                    return criteriaBuilder.like(propertyAlias, '%' + oneOperandStringCondition.getValue());
                case startswith:
                    return criteriaBuilder.like(propertyAlias, oneOperandStringCondition.getValue() + '%');
            }
        } else if (propertyValue instanceof ITwoOperandComparableCondition<?>) {
            ITwoOperandComparableCondition<?> twoOperandComparableCondition = (ITwoOperandComparableCondition<?>) propertyValue;
            TwoOperandComparableConditionImpl.Operator operator = twoOperandComparableCondition.getOperator();
            Comparable lowerValueInclusive = (Comparable<?>) twoOperandComparableCondition.getLowerValueInclusive();
            Comparable higherValueExclusive = (Comparable<?>) twoOperandComparableCondition.getHigherValueExclusive();
            switch (operator) {
                case between:
                    return criteriaBuilder.between(propertyAlias, lowerValueInclusive, higherValueExclusive);
            }
        } else if (propertyValue instanceof IMultiOperandGeneralCondition<?>) {
            IMultiOperandGeneralCondition<?> multiOperandGeneralCondition = (IMultiOperandGeneralCondition<?>) propertyValue;
            MultiOperandGeneralConditionImpl.Operator operator = multiOperandGeneralCondition.getOperator();
            switch (operator) {
                case in:
                    Collection valueCollection = multiOperandGeneralCondition.getValueCollection();
                    return criteriaBuilder.isTrue(propertyAlias.in(valueCollection));
            }
        } else if (propertyValue instanceof ISingleOperandLogicalCondition<?>) {
            ISingleOperandLogicalCondition<?> singleOperandLogicalCondition = (ISingleOperandLogicalCondition<?>) propertyValue;
            SingleOperandLogicalConditionImpl.Operator operator = singleOperandLogicalCondition.getOperator();
            switch (operator) {
                case not:
                    IBasePropertyCondition<?, ?> operand = singleOperandLogicalCondition.getOperand();
                    Predicate operandExpr = getPropertyCriterion(propertyAlias, operand, criteriaBuilder);
                    return criteriaBuilder.not(operandExpr);
            }
        } else if (propertyValue instanceof IMultiOperandLogicalCondition<?>) {
            IMultiOperandLogicalCondition<?> multiOperandLogicalCondition = (IMultiOperandLogicalCondition<?>) propertyValue;
            MultiOperandLogicalConditionImpl.Operator operator = multiOperandLogicalCondition.getOperator();
            Collection<? extends IBasePropertyCondition<?, ?>> operandCollection = multiOperandLogicalCondition.getOperandCollection();
            List<Predicate> predicates = new ArrayList<>();
            for (IBasePropertyCondition<?, ?> basePropertyCondition : operandCollection) {
                Predicate expression = getPropertyCriterion(propertyAlias, basePropertyCondition, criteriaBuilder);
                predicates.add(expression);
            }
            switch (operator) {
                case and:
                    return andAll(criteriaBuilder, predicates);
                case or:
                    return orAll(criteriaBuilder, predicates);
            }
        }
        return null;
    }

    @Override
    default Predicate andAll(CriteriaBuilder criteriaBuilder, List<Predicate> criterionList) {
        return criteriaBuilder.and(criterionList.toArray(new Predicate[0]));
    }

    @Override
    default Predicate orAll(CriteriaBuilder criteriaBuilder, List<Predicate> criterionList) {
        return criteriaBuilder.or(criterionList.toArray(new Predicate[0]));
    }

}
