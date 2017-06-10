package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.twoop;

/**
 * Created by amv on 12/7/16.
 */
public class TwoOperandComparableConditionImpl<PropType>
        extends BaseTwoOperandConditionImpl<TwoOperandComparableConditionImpl.Operator, PropType>
        implements ITwoOperandComparableCondition<PropType> {

    public enum Operator {
        between;
    }

    public TwoOperandComparableConditionImpl(Operator operator, PropType lowerValueInclusive, PropType higherValueExclusive) {
        super(operator, lowerValueInclusive, higherValueExclusive);
    }
}