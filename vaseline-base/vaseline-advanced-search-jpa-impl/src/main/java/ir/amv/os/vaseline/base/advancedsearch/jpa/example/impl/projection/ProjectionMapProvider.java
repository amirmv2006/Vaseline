package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.SearchJoinType;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import javax.persistence.criteria.*;
import java.util.HashMap;

/**
 * Created by amv on 12/12/16.
 */
public class ProjectionMapProvider<E>
        extends HashMap<String, From>
        implements IJpaCriteriaFromProvider {

    CriteriaQuery<E> query;
    Class<E> entityClass;

    public ProjectionMapProvider(CriteriaQuery<E> query, Class<E> entityClass) {
        this.query = query;
        this.entityClass = entityClass;
    }

    @Override
    public From getFrom(String propTN, SearchJoinType joinType) {
        From path = super.get(propTN);
        if (path == null) {
            if (propTN.equals("")) {
                Class<?> propertyTypeByTreeName = ReflectionUtil.getPropertyTypeByTreeName(entityClass, propTN);
                path = query.from(propertyTypeByTreeName);
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
                path = get(parent).join(child, jpaJoinType);
            }

            put(propTN, path);
        }
        return path;
    }
}
