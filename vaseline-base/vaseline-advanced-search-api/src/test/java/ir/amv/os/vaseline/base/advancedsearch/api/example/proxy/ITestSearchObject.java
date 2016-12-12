package ir.amv.os.vaseline.base.advancedsearch.api.example.proxy;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;

/**
 * Created by amv on 12/8/16.
 */
public interface ITestSearchObject {

    IBasePropertyCondition<?, String> getStringProperty();
    void setStringProperty(IBasePropertyCondition<?, String> stringProperty);

    IBasePropertyCondition<?, Integer> getIntegerProperty();
    void setIntegerProperty(IBasePropertyCondition<?, Integer> stringProperty);
}
