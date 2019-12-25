package ir.amv.os.vaseline.data.advanced.search.api.model.condition.oneop;

/**
 * Created by amv on 12/7/16.
 */
public class BaseOneOperandConditionImpl<Operator, PropType>
        implements IBaseOneOperandCondition<Operator, PropType> {

    private Operator operator;
    private PropType value;

    public BaseOneOperandConditionImpl(Operator operator, PropType value) {
        this.operator = operator;
        this.value = value;
    }

    @Override
    public PropType getValue() {
        return value;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }
}
