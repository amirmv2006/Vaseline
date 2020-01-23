package ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl;

import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntityDeleteValidation;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.validation.IEntityUpdateValidation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * Created by AMV on 2/14/2016.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseDtoImpl<I> extends BaseEmptyDtoImpl implements IBaseDto<I> {

    @NotNull(groups = {IEntityUpdateValidation.class, IEntityDeleteValidation.class})
    private I id;

}
