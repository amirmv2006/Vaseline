package ir.amv.os.vaseline.security.authorization.rbac.model.def.user;

import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.role.SecurityRoleEntity;

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
public class SecurityUserEntity
        extends VaselineBaseUserEntity
        implements ISecurityUserWithRoleEntity<SecurityRoleEntity> {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<SecurityRoleEntity> roles;

    @Override
    public Set<SecurityRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(final Set<SecurityRoleEntity> roles) {
        this.roles = roles;
    }
}
