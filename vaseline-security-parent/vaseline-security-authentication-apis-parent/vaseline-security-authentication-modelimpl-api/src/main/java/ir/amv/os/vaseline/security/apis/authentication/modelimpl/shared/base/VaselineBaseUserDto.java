package ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseDtoImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseHasPasswordUserDto;

/**
 * @author Amir
 */
public class VaselineBaseUserDto
        extends BaseDtoImpl<Long>
        implements IBaseHasPasswordUserDto {
    private String username;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(final String password) {
        this.password = password;
    }
}
