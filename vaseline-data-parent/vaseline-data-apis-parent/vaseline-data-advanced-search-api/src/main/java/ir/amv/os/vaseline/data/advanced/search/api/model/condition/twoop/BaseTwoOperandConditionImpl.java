package ir.amv.os.vaseline.data.advanced.search.api.model.condition.twoop;

/**
 * Created by amv on 12/7/16.
 */
public class BaseTwoOperandConditionImpl<Operator, PropType>
        implements IBaseTwoOperandCondition<Operator, PropType> {

    private Operator operator;
    private PropType lowerValueInclusive;
    private PropType higherValueExclusive;

    public BaseTwoOperandConditionImpl(Operator operator, PropType lowerValueInclusive, PropType higherValueExclusive) {
        this.operator = operator;
        this.lowerValueInclusive = lowerValueInclusive;
        this.higherValueExclusive = higherValueExclusive;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public PropType getLowerValueInclusive() {
        return lowerValueInclusive;
    }

    @Override
    public PropType getHigherValueExclusive() {
        return higherValueExclusive;
    }
}
