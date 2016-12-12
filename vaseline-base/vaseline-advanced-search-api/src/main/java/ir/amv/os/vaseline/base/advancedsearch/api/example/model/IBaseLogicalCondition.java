package ir.amv.os.vaseline.base.advancedsearch.api.example.model;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.twoop.TwoOperandComparableConditionImpl;

/**
 * Created by amv on 12/7/16.
 */
public interface IBaseLogicalCondition<PT>
        extends IBasePropertyCondition<TwoOperandComparableConditionImpl.Operator, PT> {

    IBaseLogicalCondition<PT> and(IBaseLogicalCondition<PT>... conditions);
    IBaseLogicalCondition<PT> or(IBaseLogicalCondition<PT>... conditions);
    IBaseLogicalCondition<PT> not();
}
