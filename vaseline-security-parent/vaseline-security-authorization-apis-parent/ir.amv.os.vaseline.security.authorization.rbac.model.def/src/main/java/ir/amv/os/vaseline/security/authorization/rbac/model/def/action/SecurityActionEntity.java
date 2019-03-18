package ir.amv.os.vaseline.security.authorization.rbac.model.def.action;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseEntityImpl;
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
public class SecurityActionEntity
        extends BaseEntityImpl<Long>
        implements ISecurityAction<SecurityActionEntity> {

    @Column(unique = true)
    private String actionTreeName;
    @ManyToOne
    private SecurityActionEntity parent;

    @Override
    public String getActionTreeName() {
        return actionTreeName;
    }

    @Override
    public SecurityActionEntity getParent() {
        return parent;
    }

    public void setActionTreeName(final String actionTreeName) {
        this.actionTreeName = actionTreeName;
    }

    public void setParent(final SecurityActionEntity parent) {
        this.parent = parent;
    }
}
