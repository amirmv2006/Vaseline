package ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user;

import ir.amv.os.vaseline.basics.apis.core.api.shared.base.dto.base.baseimpl.BaseDtoImpl;

import java.util.Date;

/**
 * Created by AMV on 2/16/2016.
 */
public class BaseUserDto extends BaseDtoImpl<Long> {

    private String username;
    private String password;
    private Boolean enabled;
    private Date lastLoginTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
