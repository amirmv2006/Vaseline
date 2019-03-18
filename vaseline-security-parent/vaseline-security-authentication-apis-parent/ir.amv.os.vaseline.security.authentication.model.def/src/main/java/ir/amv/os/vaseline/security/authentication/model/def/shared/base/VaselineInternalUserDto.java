package ir.amv.os.vaseline.security.authentication.model.def.shared.base;

import ir.amv.os.vaseline.security.authentication.model.api.shared.base.IBaseHasPasswordUserDto;

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
