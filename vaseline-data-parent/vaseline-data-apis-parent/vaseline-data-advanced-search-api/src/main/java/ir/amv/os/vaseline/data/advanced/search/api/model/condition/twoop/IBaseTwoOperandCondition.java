package ir.amv.os.vaseline.data.advanced.search.api.model.condition.twoop;

import ir.amv.os.vaseline.data.advanced.search.api.model.IBasePropertyCondition;

/**
 * Created by amv on 12/7/16.
 */
public interface IBaseTwoOperandCondition<Operator, PropType>
        extends IBasePropertyCondition<Operator, PropType> {

    PropType getLowerValueInclusive();
    PropType getHigherValueExclusive();
}
