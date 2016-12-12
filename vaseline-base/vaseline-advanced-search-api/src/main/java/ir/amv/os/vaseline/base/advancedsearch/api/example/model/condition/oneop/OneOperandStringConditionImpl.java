package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop;

/**
 * Created by amv on 12/7/16.
 */
public class OneOperandStringConditionImpl
        extends BaseOneOperandConditionImpl<OneOperandStringConditionImpl.Operator, String>
        implements IOneOperandStringCondition {

    public enum Operator {
        contains,
        startswith,
        endswith;
    }

    public OneOperandStringConditionImpl(Operator operator, String value) {
        super(operator, value);
    }
}
