package ir.amv.os.vaseline.data.hibernate.apis.dao.server.projection;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.basic.server.from.SearchJoinType;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.sql.JoinType;

import java.beans.PropertyDescriptor;
import java.util.HashMap;

/**
 * Created by AMV on 10/4/2017.
 */
public class DefaultHibernateCriteriaProjectionProviderImpl
        extends HashMap<String, String>
        implements IHibernateCriteriaProjectionProvider {

    private DetachedCriteria detachedCriteria;
    private Class<?> entityClass;

    public DefaultHibernateCriteriaProjectionProviderImpl(DetachedCriteria detachedCriteria, final Class<?>
            entityClass) {
        this.detachedCriteria = detachedCriteria;
        this.entityClass = entityClass;
        put("", "");
    }

    @Override
    public String getCriteriaParentProjection(String propertyPtn, SearchJoinType joinType) {
        if (propertyPtn.matches(".*\\d.*")) {
            propertyPtn = propertyPtn.replaceAll("\\d", "");
        }
        String alias = super.get(propertyPtn);
        if (alias == null) {
            JoinType hibernateJoinType = JoinType.INNER_JOIN;
            if (joinType != null) {
                switch (joinType) {
                    case LEFT:
                        hibernateJoinType = JoinType.LEFT_OUTER_JOIN;
                        break;
                    case RIGHT:
                        hibernateJoinType = JoinType.RIGHT_OUTER_JOIN;
                        break;
                }
            }
            PropertyDescriptor propertyDescriptor = ReflectionUtil.getPropertyDescriptorByTreeName(entityClass,
                    propertyPtn);
            Class<?> propertyType = ReflectionUtil.searchMethodReturnType(propertyDescriptor.getReadMethod(), IBaseEntity.class);
            if (propertyPtn.contains(".")) {
                int lastIndexOfDot = propertyPtn.lastIndexOf('.');
                String parentPtn = propertyPtn.substring(0, lastIndexOfDot);
                String parentAlias = getCriteriaParentProjection(parentPtn, joinType);
                alias = parentAlias + propertyPtn.substring(lastIndexOfDot);
            } else {
                alias = propertyPtn;
            }
            if (propertyType != null) {
                String realAlias = alias.replace('.', '_');
                detachedCriteria.createAlias(
                        alias,
                        realAlias,
                        hibernateJoinType);
                alias = realAlias;
            }
            put(propertyPtn, alias);
        }
        return alias;
    }
}
