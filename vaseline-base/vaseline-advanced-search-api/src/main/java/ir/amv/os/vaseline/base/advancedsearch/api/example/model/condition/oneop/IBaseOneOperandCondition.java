package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;

/**
 * Created by amv on 12/7/16.
 */
public interface IBaseOneOperandCondition<Operator, PropType>
        extends IBasePropertyCondition<Operator, PropType> {
    PropType getValue();
}
