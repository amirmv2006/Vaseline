package ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.hibernate.action;

import ir.amv.os.vaseline.data.hibernate.apis.dao.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityActionHibernateDao<A extends ISecurityAction<A>>
        extends IBaseImplementedHibernateReadOnlyDao<A, Long>,
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
