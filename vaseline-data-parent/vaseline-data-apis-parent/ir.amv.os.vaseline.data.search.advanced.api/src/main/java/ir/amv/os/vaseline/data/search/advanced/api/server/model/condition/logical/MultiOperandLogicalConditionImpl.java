package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public class MultiOperandLogicalConditionImpl<PropType>
        implements IMultiOperandLogicalCondition<PropType> {
    public enum Operator {
        and,
        or;
    }

    private Operator operator;
    private Collection<IBasePropertyCondition<?, PropType>> operands;

    public MultiOperandLogicalConditionImpl(Operator operator, IBasePropertyCondition<?, PropType>... operands) {
        this.operator = operator;
        this.operands = Arrays.asList(operands);
    }

    public MultiOperandLogicalConditionImpl(Operator operator, Collection<IBasePropertyCondition<?, PropType>> operands) {
        this.operator = operator;
        this.operands = operands;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public Collection<IBasePropertyCondition<?, PropType>> getOperandCollection() {
        return operands;
    }
}
