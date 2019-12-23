package ir.amv.os.vaseline.security.authorization.rbac.model.def.role;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseBusinessModelImpl;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.role.ISecurityRole;
import ir.amv.os.vaseline.security.authorization.rbac.model.def.action.SecurityActionBusinessModel;

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
public class SecurityRoleBusinessModel
        extends BaseBusinessModelImpl<Long>
        implements ISecurityRole<SecurityActionBusinessModel> {

    @Column(unique = true)
    private String roleName;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<SecurityActionBusinessModel> actions;

    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public Set<SecurityActionBusinessModel> getActions() {
        return actions;
    }

    public void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    public void setActions(final Set<SecurityActionBusinessModel> actions) {
        this.actions = actions;
    }
}
