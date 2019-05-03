package ir.amv.os.vaseline.data.osgi.test.jpa.model;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.baseimpl.BaseDtoImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto extends BaseDtoImpl<Long> {
    private String firstName;
    private String lastName;
}
