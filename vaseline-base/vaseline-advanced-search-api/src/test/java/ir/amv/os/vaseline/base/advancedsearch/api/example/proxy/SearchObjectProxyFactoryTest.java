package ir.amv.os.vaseline.base.advancedsearch.api.example.proxy;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop.OneOperandComparableConditionImpl;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.oneop.OneOperandGeneralConditionImpl;
import org.junit.Test;

import static ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.PropertyConditions.equlas;
import static ir.amv.os.vaseline.base.advancedsearch.api.example.model.condition.PropertyConditions.greaterThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by amv on 12/8/16.
 */
public class SearchObjectProxyFactoryTest {

    @Test
    public void testProxy() {
        ITestSearchObject proxy = SearchObjectProxyFactory.proxy(ITestSearchObject.class);
        proxy.setStringProperty(equlas("Test"));
        IBasePropertyCondition<?, String> stringProperty = proxy.getStringProperty();
        assertTrue(stringProperty instanceof OneOperandGeneralConditionImpl);
        OneOperandGeneralConditionImpl<String> casted = (OneOperandGeneralConditionImpl<String>) stringProperty;
        assertEquals("Test", casted.getValue());
        proxy.setIntegerProperty(greaterThan(18));
        IBasePropertyCondition<?, Integer> integerProperty = proxy.getIntegerProperty();
        assertTrue(integerProperty instanceof OneOperandComparableConditionImpl);
        OneOperandComparableConditionImpl<Integer> intCasted = (OneOperandComparableConditionImpl<Integer>) integerProperty;
        assertEquals(18, intCasted.getValue(), 0);
    }
}
