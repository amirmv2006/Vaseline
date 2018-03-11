package ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Amir
 */
@MappedSuperclass
public class VaselineBaseUserEntity
        extends BaseEntityImpl<Long>
        implements IBaseUserEntity {
    @Column(name = "USERNAME", unique = true)
    private String username;

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
