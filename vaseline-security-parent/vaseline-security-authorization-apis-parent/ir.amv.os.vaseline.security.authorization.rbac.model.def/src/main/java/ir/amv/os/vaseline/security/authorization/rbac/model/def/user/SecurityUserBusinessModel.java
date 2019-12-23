package ir.amv.os.vaseline.security.authorization.rbac.model.def.user;

import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineBaseUserBusinessModel;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleBusinessModel;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleBusinessModel;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Amir
 */
@Entity
@Table(name = "SEC_VASELINE_USER")
@Access(AccessType.FIELD)
@Cacheable
public class SecurityUserBusinessModel
        extends VaselineBaseUserBusinessModel
        implements ISecurityUserWithRoleBusinessModel<SecurityRoleBusinessModel> {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<SecurityRoleBusinessModel> roles;

    @Override
    public Set<SecurityRoleBusinessModel> getRoles() {
        return roles;
    }

    public void setRoles(final Set<SecurityRoleBusinessModel> roles) {
        this.roles = roles;
    }
}
