package ir.amv.os.vaseline.security.authentication.model.api.shared.base;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;

/**
 * @author Amir
 */
public interface IBaseUserDto
        extends IBaseDto<Long> {

    String getUsername();
    void setUsername(String username);

}
