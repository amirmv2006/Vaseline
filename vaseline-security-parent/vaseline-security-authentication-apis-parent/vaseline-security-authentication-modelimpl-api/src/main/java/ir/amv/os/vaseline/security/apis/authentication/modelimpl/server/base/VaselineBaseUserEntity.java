package ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Amir
 */
@Entity
@Table(name = "SEC_VASELINE_BASE_USER")
@Access(AccessType.FIELD)
public class VaselineBaseUserEntity extends BaseEntityImpl<Long>
        implements IBaseUserEntity {
    @Column(name = "USERNAME", unique = true)
    private String username;
    @Column(name = "PASSWORD")
    private String password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
