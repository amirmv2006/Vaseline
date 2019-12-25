package ir.amv.os.vaseline.data.advanced.search.api.model.condition.multiop;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by amv on 12/8/16.
 */
public class MultiOperandGeneralConditionImpl<PropType>
        implements IMultiOperandGeneralCondition<PropType> {
    public enum Operator {
        in;
    }

    private Operator operator;

    private Collection<PropType> valueCollection;

    public MultiOperandGeneralConditionImpl(Operator operator, PropType... valueCollection) {
        this.operator = operator;
        this.valueCollection = Arrays.asList(valueCollection);
    }

    public MultiOperandGeneralConditionImpl(Operator operator, Collection<PropType> valueCollection) {
        this.operator = operator;
        this.valueCollection = valueCollection;
    }

    @Override
    public Operator getOperator() {
        return operator;
    }

    @Override
    public Collection<PropType> getValueCollection() {
        return valueCollection;
    }
}
