package ir.amv.os.vaseline.security.authentication.model.def.server.base;

import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseHasPasswordUserBusinessModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Amir
 */
@Entity
@Table(name = "SEC_VASELINE_BASE_USER")
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.JOINED)
@Cacheable
public class VaselineInternalUserBusinessModel
        extends VaselineBaseUserBusinessModel
        implements IBaseHasPasswordUserBusinessModel {
    @Column(name = "PASSWORD")
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
