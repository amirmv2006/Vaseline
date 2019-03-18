package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.func;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/12/16.
 */
public interface IBaseApplyFunctionCondition<PropType>
        extends IBasePropertyCondition<BaseApplyFunctionConditionImpl.Operator, PropType> {
    IBasePropertyCondition<?, PropType> getCondition();
}
