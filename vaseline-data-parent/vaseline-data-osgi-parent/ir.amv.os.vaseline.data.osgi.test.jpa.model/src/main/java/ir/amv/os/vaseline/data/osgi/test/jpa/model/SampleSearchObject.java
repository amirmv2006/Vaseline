package ir.amv.os.vaseline.data.osgi.test.jpa.model;

import ir.amv.os.vaseline.data.search.advanced.api.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleSearchObject extends BaseSearchObjectImpl {
    private IBasePropertyCondition<?, String> firstName;
    private IBasePropertyCondition<?, String> lastName;
}
