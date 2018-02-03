package ir.amv.os.vaseline.security.apis.authentication.dao.jpa.server.base.modelimpl;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Amir
 */
@Table(name = "VASELINE_BASEUSER")
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
@Entity
public class BaseUserEntityImpl
        extends BaseEntityImpl<Long>
        implements IBaseUserEntity {

    private String username;
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
