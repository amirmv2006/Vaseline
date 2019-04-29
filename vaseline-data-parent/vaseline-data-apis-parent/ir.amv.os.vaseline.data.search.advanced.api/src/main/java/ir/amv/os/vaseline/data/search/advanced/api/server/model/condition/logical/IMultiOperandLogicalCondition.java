package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.logical;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public interface IMultiOperandLogicalCondition<PropType>
        extends IBasePropertyCondition<MultiOperandLogicalConditionImpl.Operator, PropType> {

    Collection<IBasePropertyCondition<?, PropType>> getOperandCollection();
}