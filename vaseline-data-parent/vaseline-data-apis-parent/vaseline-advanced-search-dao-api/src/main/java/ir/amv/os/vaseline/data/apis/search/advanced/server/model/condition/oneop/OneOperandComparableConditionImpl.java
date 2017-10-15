package ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.oneop;

/**
 * Created by amv on 12/7/16.
 */
public class OneOperandComparableConditionImpl<PropType>
        extends BaseOneOperandConditionImpl<OneOperandComparableConditionImpl.Operator, PropType>
        implements IOneOperandComparableCondition<PropType> {

    public enum Operator {
        gt,
        gtEq,
        lt,
        ltEq
    }

    public OneOperandComparableConditionImpl(Operator baseOperator, PropType value) {
        super(baseOperator, value);
    }
}
