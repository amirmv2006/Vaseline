package ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.ent.BaseEntityImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by AMV on 2/16/2016.
 */
@Table(name = "VASELINE_BASEUSER")
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
public class BaseUserEntity extends BaseEntityImpl<Long> {

    private String username;
    private String password;
    private Boolean enabled;
    private Date lastLoginTime;

    @Column(unique = true, nullable = false)
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
