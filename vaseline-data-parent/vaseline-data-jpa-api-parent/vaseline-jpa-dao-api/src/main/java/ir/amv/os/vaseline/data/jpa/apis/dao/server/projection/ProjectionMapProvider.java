package ir.amv.os.vaseline.data.jpa.apis.dao.server.projection;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.server.from.SearchJoinType;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import java.util.HashMap;

/**
 * Created by amv on 12/12/16.
 */
public class ProjectionMapProvider<E, Q>
        extends HashMap<String, Path>
        implements IJpaCriteriaFromProvider {

    CriteriaQuery<Q> query;
    Class<E> entityClass;

    public ProjectionMapProvider(CriteriaQuery<Q> query, Class<E> entityClass) {
        this.query = query;
        this.entityClass = entityClass;
    }

    @Override
    public Path getCriteriaParentProjection(String propTN, SearchJoinType joinType) {
        Path path = super.get(propTN);
        if (path == null) {
            if (propTN.equals("")) {
                path = query.from(entityClass);
            } else {
                String parent;
                String child;
                if (propTN.contains(".")) {
                    int lastDot = propTN.lastIndexOf(".");
                    parent = propTN.substring(0, lastDot);
                    child = propTN.substring(lastDot + 1);
                } else {
                    parent = "";
                    child = propTN;
                }
                JoinType jpaJoinType = JoinType.INNER;
                if (joinType != null) {
                    switch (joinType) {
                        case LEFT: jpaJoinType = JoinType.LEFT;
                            break;
                        case RIGHT:jpaJoinType = JoinType.RIGHT;
                            break;
                    }
                }
                Class<?> propertyType = ReflectionUtil.getPropertyTypeByTreeName(entityClass, propTN);
                if (IBaseEntity.class.isAssignableFrom(propertyType)) {
                    path = ((From) getCriteriaParentProjection(parent)).join(child, jpaJoinType);
                } else {
                    path = getCriteriaParentProjection(parent).get(child);
                }
            }

            put(propTN, path);
        }
        return path;
    }
}
