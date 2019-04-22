package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/8/16.
 */
public interface ISingleOperandLogicalCondition<PropType>
        extends IBasePropertyCondition<SingleOperandLogicalConditionImpl.Operator, PropType> {

    IBasePropertyCondition<?, PropType> getOperand();
}
