package ir.amv.os.vaseline.security.authorization.rbac.dao.def.hibernate.action;

import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultSecurityActionHibernateDao<A extends ISecurityAction<A>>
        extends IDefaultHibernateReadOnlyDao<A, Long>,
        ISecurityActionDao<A> {

    @Override
    default List<String> getActionChildTreeNames(final String baseActionTN) {
        DetachedCriteria detachedCriteria = createCriteria();
        DefaultHibernateCriteriaProjectionProviderImpl projectionProvider =
                new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria, getEntityClass());
        detachedCriteria.add(Restrictions.eq(
                projectionProvider.getCriteriaParentProjection("parent.actionTreeName"), baseActionTN)
        );
        detachedCriteria.setProjection(Projections.property("actionTreeName"));
        Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
        return criteria.list();
    }
}
