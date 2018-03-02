package ir.amv.os.vaseline.security.apis.authentication.model.shared.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;

/**
 * @author Amir
 */
public interface IBaseUserDto
        extends IBaseDto<Long> {

    String getUsername();
    void setUsername(String username);

}
