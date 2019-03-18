package ir.amv.os.vaseline.security.authentication.model.def.shared.base;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.baseimpl.BaseDtoImpl;
import ir.amv.os.vaseline.security.authentication.model.api.shared.base.IBaseUserDto;

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
