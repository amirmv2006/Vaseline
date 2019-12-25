package ir.amv.os.vaseline.data.advanced.search.api.model.condition;

import ir.amv.os.vaseline.data.advanced.search.api.model.condition.oneop.OneOperandStringConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.twoop.TwoOperandComparableConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBasePropertyCondition;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.func.BaseApplyFunctionConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.logical.MultiOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.logical.SingleOperandLogicalConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.multiop.MultiOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.noop.NoOperandGeneralConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.oneop.OneOperandComparableConditionImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.condition.oneop.OneOperandGeneralConditionImpl;

/**
 * Created by amv on 12/7/16.
 */
public class PropertyConditions {

    public static IBasePropertyCondition<?, String> upper(IBasePropertyCondition<?, String> condition) {
        return new BaseApplyFunctionConditionImpl<String>(BaseApplyFunctionConditionImpl.Operator.upperCase, condition);
    }

    public static IBasePropertyCondition<?, String> lower(IBasePropertyCondition<?, String> condition) {
        return new BaseApplyFunctionConditionImpl<String>(BaseApplyFunctionConditionImpl.Operator.lowerCase, condition);
    }

    public static <PropType> IBasePropertyCondition<?, PropType> alwaysTrue() {
        return new NoOperandGeneralConditionImpl<PropType>(NoOperandGeneralConditionImpl.Operator.alwaysTrue);
    }
    public static <PropType> IBasePropertyCondition<?, PropType> alwaysFalse() {
        return new NoOperandGeneralConditionImpl<PropType>(NoOperandGeneralConditionImpl.Operator.alwaysFalse);
    }
    public static <PropType> IBasePropertyCondition<?, PropType> isNull() {
        return new NoOperandGeneralConditionImpl<PropType>(NoOperandGeneralConditionImpl.Operator.isNull);
    }
    public static <PropType> IBasePropertyCondition<?, PropType> isNotNull() {
        return new NoOperandGeneralConditionImpl<PropType>(NoOperandGeneralConditionImpl.Operator.isNotNull);
    }

    public static <PropType> IBasePropertyCondition<?, PropType> equlas(PropType value) {
        return new OneOperandGeneralConditionImpl<PropType>(OneOperandGeneralConditionImpl.Operator.eq, value);
    }

    public static <PropType> IBasePropertyCondition<?, PropType> notEqulas(PropType value) {
        return new OneOperandGeneralConditionImpl<PropType>(OneOperandGeneralConditionImpl.Operator.neq, value);
    }

    public static <PropType extends Comparable<?>> IBasePropertyCondition<?, PropType> lessThan(PropType value) {
        return new OneOperandComparableConditionImpl<PropType>(OneOperandComparableConditionImpl.Operator.lt, value);
    }

    public static <PropType extends Comparable<?>> IBasePropertyCondition<?, PropType> lessThanOrEqualsTo(PropType value) {
        return new OneOperandComparableConditionImpl<PropType>(OneOperandComparableConditionImpl.Operator.ltEq, value);
    }

    public static <PropType extends Comparable<?>> IBasePropertyCondition<?, PropType> greaterThan(PropType value) {
        return new OneOperandComparableConditionImpl<PropType>(OneOperandComparableConditionImpl.Operator.gt, value);
    }

    public static <PropType extends Comparable<?>> IBasePropertyCondition<?, PropType> greaterThanOrEqualsTo(PropType value) {
        return new OneOperandComparableConditionImpl<PropType>(OneOperandComparableConditionImpl.Operator.gtEq, value);
    }

    public static <PropType extends Comparable<?>> IBasePropertyCondition<?, PropType> between(PropType lowValIncl, PropType highValExcl) {
        return new TwoOperandComparableConditionImpl<PropType>(TwoOperandComparableConditionImpl.Operator.between, lowValIncl, highValExcl);
    }

    public static IBasePropertyCondition<?, String> contains(String value) {
        return new OneOperandStringConditionImpl(OneOperandStringConditionImpl.Operator.contains, value);
    }
    public static IBasePropertyCondition<?, String> endswith(String value) {
        return new OneOperandStringConditionImpl(OneOperandStringConditionImpl.Operator.endswith, value);
    }
    public static IBasePropertyCondition<?, String> startswith(String value) {
        return new OneOperandStringConditionImpl(OneOperandStringConditionImpl.Operator.startswith, value);
    }

    public static <PropType> IBasePropertyCondition<?, PropType> in(PropType... values) {
        if (values == null) {
            return alwaysFalse();
        }
        return new MultiOperandGeneralConditionImpl<PropType>(MultiOperandGeneralConditionImpl.Operator.in, values);
    }

    public static <PropType> IBasePropertyCondition<?, PropType> and(IBasePropertyCondition<?, PropType>... operands) {
        if (operands == null) {
            return alwaysTrue();
        }
        return new MultiOperandLogicalConditionImpl<PropType>(MultiOperandLogicalConditionImpl.Operator.and, operands);
    }
    public static <PropType> IBasePropertyCondition<?, PropType> or(IBasePropertyCondition<?, PropType>... operands) {
        if (operands == null) {
            return alwaysTrue();
        }
        return new MultiOperandLogicalConditionImpl<PropType>(MultiOperandLogicalConditionImpl.Operator.or, operands);
    }
    public static <PropType> IBasePropertyCondition<?, PropType> not(IBasePropertyCondition<?, PropType> operand) {
        return new SingleOperandLogicalConditionImpl<PropType>(SingleOperandLogicalConditionImpl.Operator.not, operand);
    }

}
