package ir.amv.os.vaseline.security.authorization.rbac.model.def.action;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseBusinessModelImpl;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Amir
 */
@Entity
@Table(name = "SEC_VASELINE_ACTION")
@Access(AccessType.FIELD)
public class SecurityActionBusinessModel
        extends BaseBusinessModelImpl<Long>
        implements ISecurityAction<SecurityActionBusinessModel> {

    @Column(unique = true)
    private String actionTreeName;
    @ManyToOne
    private SecurityActionBusinessModel parent;

    @Override
    public String getActionTreeName() {
        return actionTreeName;
    }

    @Override
    public SecurityActionBusinessModel getParent() {
        return parent;
    }

    public void setActionTreeName(final String actionTreeName) {
        this.actionTreeName = actionTreeName;
    }

    public void setParent(final SecurityActionBusinessModel parent) {
        this.parent = parent;
    }
}
