package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBasePropertyCondition;
import ir.amv.os.vaseline.base.advancedsearch.api.example.model.IBaseSearchObject;

/**
 * Created by amv on 12/12/16.
 */
public interface ICitySearchObject extends IBaseSearchObject {

    IBasePropertyCondition<?, String> getName();
    void setName(IBasePropertyCondition<?, String> name);

    IBasePropertyCondition<?, String> getState();
    void setState(IBasePropertyCondition<?, String> state);

    IBasePropertyCondition<?, String> getCountry();
    void setCountry(IBasePropertyCondition<?, String> country);

    IBasePropertyCondition<?, String> getMap();
    void setMap(IBasePropertyCondition<?, String> map);

}
