package ir.amv.os.vaseline.data.advanced.search.api.model.condition.func;

import ir.amv.os.vaseline.data.advanced.search.api.model.IBasePropertyCondition;

/**
 * Created by amv on 12/12/16.
 */
public interface IBaseApplyFunctionCondition<PropType>
        extends IBasePropertyCondition<BaseApplyFunctionConditionImpl.Operator, PropType> {
    IBasePropertyCondition<?, PropType> getCondition();
}
