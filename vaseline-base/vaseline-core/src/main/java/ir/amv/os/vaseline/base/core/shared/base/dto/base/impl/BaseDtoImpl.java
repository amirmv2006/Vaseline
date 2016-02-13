package ir.amv.os.vaseline.base.core.shared.base.dto.base.impl;

import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;

/**
 * Created by AMV on 2/14/2016.
 */
public class BaseDtoImpl<Id> extends BaseEmptyDtoImpl implements IBaseDto<Id> {

    private Id id;

    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
    }
}
