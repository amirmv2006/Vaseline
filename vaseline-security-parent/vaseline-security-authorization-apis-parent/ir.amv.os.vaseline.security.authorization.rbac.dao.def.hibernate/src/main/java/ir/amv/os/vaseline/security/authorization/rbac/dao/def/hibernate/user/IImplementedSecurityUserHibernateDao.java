package ir.amv.os.vaseline.security.authorization.rbac.dao.def.hibernate.user;

import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityUserHibernateDao<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseImplementedHibernateReadOnlyDao<U, Long>,
        ISecurityUserDao<U> {

    @Override
    default List<String> getUserActionTreeNames(String username) {
        DetachedCriteria detachedCriteria = createCriteria();
        DefaultHibernateCriteriaProjectionProviderImpl projectionProvider =
                new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria, getEntityClass());
        detachedCriteria.add(Restrictions.eq(
                "username", username)
        );
        detachedCriteria.setProjection(Projections.property(
                projectionProvider.getCriteriaParentProjection("roles.actions.actionTreeName")
        ));
        Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
        return criteria.list();
    }

    @Override
    default List<String> getUsernamesWithAccessToActionTreeName(final String... actionTreeNames) {
        if (actionTreeNames == null) {
            return null;
        }
        DetachedCriteria detachedCriteria = createCriteria();
        DefaultHibernateCriteriaProjectionProviderImpl projectionProvider =
                new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria, getEntityClass());
        Disjunction disjunction = Restrictions.disjunction();
        for (String actionTreeName : actionTreeNames) {
            disjunction.add(Restrictions.eq(
                    projectionProvider.getCriteriaParentProjection("roles.actions.actionTreeName"),
                    actionTreeName));
        }
        detachedCriteria.add(disjunction);
        detachedCriteria.setProjection(Projections.property("username"));
        Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
        return criteria.list();
    }
}
