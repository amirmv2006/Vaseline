package ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseDtoImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseHasPasswordUserDto;

/**
 * @author Amir
 */
public class VaselineInternalUserDto
        extends VaselineBaseUserDto
        implements IBaseHasPasswordUserDto {
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(final String password) {
        this.password = password;
    }
}
