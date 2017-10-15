package ir.amv.os.vaseline.data.apis.search.advanced.server.model.condition.twoop;

import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBasePropertyCondition;

/**
 * Created by amv on 12/7/16.
 */
public interface IBaseTwoOperandCondition<Operator, PropType>
        extends IBasePropertyCondition<Operator, PropType> {

    PropType getLowerValueInclusive();
    PropType getHigherValueExclusive();
}
