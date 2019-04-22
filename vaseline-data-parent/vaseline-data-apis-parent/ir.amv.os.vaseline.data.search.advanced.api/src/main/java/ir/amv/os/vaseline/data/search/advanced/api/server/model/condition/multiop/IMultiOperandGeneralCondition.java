package ir.amv.os.vaseline.data.search.advanced.api.server.model.condition.multiop;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;

import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public interface IMultiOperandGeneralCondition<PropType>
        extends IBasePropertyCondition<MultiOperandGeneralConditionImpl.Operator, PropType> {

    Collection<PropType> getValueCollection();
}
