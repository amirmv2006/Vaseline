package ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.role;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.role.ISecurityRole;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author Amir
 */
@Entity
@Table(name = "SEC_VASELINE_ROLE")
@Access(AccessType.FIELD)
public class SecurityRoleEntity
        extends BaseEntityImpl<Long>
        implements ISecurityRole<SecurityActionEntity> {

    @Column(unique = true)
    private String roleName;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<SecurityActionEntity> actions;

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public Set<SecurityActionEntity> getActions() {
        return actions;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public void setActions(final Set<SecurityActionEntity> actions) {
        this.actions = actions;
    }
}
