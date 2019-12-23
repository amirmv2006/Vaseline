package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.func;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/12/16.
 */
public class BaseApplyFunctionConditionImpl<PropType>
        implements IBaseApplyFunctionCondition<PropType> {

    public enum Operator {
        upperCase,
        lowerCase
    }

    private Operator operator;
    private IBasePropertyCondition<?, PropType> condition;

    public BaseApplyFunctionConditionImpl(Operator operator, IBasePropertyCondition<?, PropType> condition) {
        this.operator = operator;
        this.condition = condition;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public IBasePropertyCondition<?, PropType> getCondition() {
        return condition;
    }
}
