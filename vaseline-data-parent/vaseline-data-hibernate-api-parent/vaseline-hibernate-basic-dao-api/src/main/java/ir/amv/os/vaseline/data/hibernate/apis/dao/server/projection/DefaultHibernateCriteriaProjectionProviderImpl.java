package ir.amv.os.vaseline.data.hibernate.apis.dao.server.projection;

import ir.amv.os.vaseline.data.apis.dao.basic.server.from.SearchJoinType;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.sql.JoinType;
import java.util.HashMap;

/**
 * Created by AMV on 10/4/2017.
 */
public class DefaultHibernateCriteriaProjectionProviderImpl
        extends HashMap<String, String>
        implements IHibernateCriteriaProjectionProvider {

    private DetachedCriteria detachedCriteria;

    public DefaultHibernateCriteriaProjectionProviderImpl(DetachedCriteria detachedCriteria) {
        this.detachedCriteria = detachedCriteria;
        put("", "");
    }

    @Override
    public String getCriteriaParentProjection(String parentPtn, SearchJoinType joinType) {
        String alias = super.get(parentPtn);
        if (alias == null) {
            JoinType hibernateJoinType = JoinType.INNER_JOIN;
            if (joinType != null) {
                switch (joinType) {
                    case LEFT: hibernateJoinType = JoinType.LEFT_OUTER_JOIN;
                        break;
                    case RIGHT:hibernateJoinType = JoinType.RIGHT_OUTER_JOIN;
                        break;
                }
            }
            if (parentPtn.contains(".")) {
                int lastIndexOfDot = parentPtn.lastIndexOf('.');
                String parentPropNameInGrandParent = parentPtn.substring(lastIndexOfDot + 1);
                String grandParentPtn = parentPtn.substring(0, lastIndexOfDot);
                String grandParentAlias = getCriteriaParentProjection(grandParentPtn);
                alias = grandParentAlias + "_" + parentPropNameInGrandParent;
                detachedCriteria.createAlias(
                        grandParentAlias + parentPtn.substring(lastIndexOfDot),
                        alias,
                        hibernateJoinType
                        );
            } else {
                alias = parentPtn;
                detachedCriteria.createAlias(parentPtn, parentPtn, hibernateJoinType);
            }
            put(parentPtn, alias);
        }
        return alias;
    }
}
