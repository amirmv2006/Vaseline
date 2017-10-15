package ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.multiop;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public interface IMultiOperandGeneralCondition<PropType>
        extends IBasePropertyCondition<MultiOperandGeneralConditionImpl.Operator, PropType> {

    Collection<PropType> getValueCollection();
}
