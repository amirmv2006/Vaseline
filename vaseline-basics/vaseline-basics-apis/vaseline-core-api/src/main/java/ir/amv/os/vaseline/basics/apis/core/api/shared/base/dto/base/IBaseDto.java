package ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseDto<Id> extends IBaseEmptyDto {

    Id getId();
    void setId(Id id);
}
