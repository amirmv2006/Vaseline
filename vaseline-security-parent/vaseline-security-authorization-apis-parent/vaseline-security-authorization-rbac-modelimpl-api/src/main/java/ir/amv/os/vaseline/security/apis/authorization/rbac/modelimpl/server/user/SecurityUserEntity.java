package ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.user;

import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role.SecurityRoleEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Entity;
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

    @OneToMany
    private Set<SecurityRoleEntity> roles;

    @Override
    public Set<SecurityRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(final Set<SecurityRoleEntity> roles) {
        this.roles = roles;
    }
}
