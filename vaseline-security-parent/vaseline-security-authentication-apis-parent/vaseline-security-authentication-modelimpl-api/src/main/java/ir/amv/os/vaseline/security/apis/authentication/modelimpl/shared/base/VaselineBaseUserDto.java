package ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.baseimpl.BaseDtoImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseUserDto;

/**
 * @author Amir
 */
public class VaselineBaseUserDto
        extends BaseDtoImpl<Long>
        implements IBaseUserDto {
    private String username;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(final String username) {
        this.username = username;
    }
}
