package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/8/16.
 */
public class SingleOperandLogicalConditionImpl<PropType>
        implements ISingleOperandLogicalCondition<PropType> {
    public enum Operator {
        not;
    }

    private Operator operator;
    private IBasePropertyCondition<?, PropType> operand;

    public SingleOperandLogicalConditionImpl(Operator operator, IBasePropertyCondition<?, PropType> operand) {
        this.operator = operator;
        this.operand = operand;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public IBasePropertyCondition<?, PropType> getOperand() {
        return operand;
    }
}
