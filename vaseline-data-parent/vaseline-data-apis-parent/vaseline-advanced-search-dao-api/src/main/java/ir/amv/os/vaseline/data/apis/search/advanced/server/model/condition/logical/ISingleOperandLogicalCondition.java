package ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.logical;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/8/16.
 */
public interface ISingleOperandLogicalCondition<PropType>
        extends IBasePropertyCondition<SingleOperandLogicalConditionImpl.Operator, PropType> {

    IBasePropertyCondition<?, PropType> getOperand();
}
