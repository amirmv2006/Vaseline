package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop;

/**
 * Created by amv on 12/7/16.
 */
public class OneOperandGeneralConditionImpl<PropType>
        extends BaseOneOperandConditionImpl<OneOperandGeneralConditionImpl.Operator, PropType>
        implements IOneOperandGeneralCondition<PropType> {

    public enum Operator {
        eq,
        neq
    }

    public OneOperandGeneralConditionImpl(Operator baseOperator, PropType value) {
        super(baseOperator, value);
    }
}
