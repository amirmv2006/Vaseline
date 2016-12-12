package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.noop;

/**
 * Created by amv on 12/7/16.
 */
public class NoOperandGeneralConditionImpl<PropType>
        implements INoOperandGeneralCondition<PropType> {
    public enum Operator {
        alwaysTrue,
        alwaysFalse,
        isNull,
        isNotNull;
    }

    private Operator operator;

    public NoOperandGeneralConditionImpl(Operator operator) {
        this.operator = operator;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

}
