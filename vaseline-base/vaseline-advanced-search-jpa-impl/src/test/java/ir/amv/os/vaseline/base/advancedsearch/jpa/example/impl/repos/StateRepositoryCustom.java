package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.repos;

import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.entity.TestState;
import ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.so.ITestStateSearchObject;

import java.util.List;

/**
 * Created by amv on 12/12/16.
 */
public interface StateRepositoryCustom {

    List<TestState> search(ITestStateSearchObject stateSearchObject);
}
