package ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.logical;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.twoop.TwoOperandComparableConditionImpl;

import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public interface IMultiOperandLogicalCondition<PropType>
        extends IBasePropertyCondition<MultiOperandLogicalConditionImpl.Operator, PropType> {

    Collection<IBasePropertyCondition<?, PropType>> getOperandCollection();
}
