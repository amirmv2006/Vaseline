package ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.action;

import ir.amv.os.vaseline.data.apis.dao.basic.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.action.ISecurityAction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityActionJpaDao<A extends ISecurityAction<A>>
        extends IBaseImplementedJpaReadOnlyDao<A, Long>,
        ISecurityActionDao<A> {

    @Override
    default List<String> getActionChildTreeNames(final String baseActionTN) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<String> query = createQuery(criteriaBuilder, String.class); // querying action tree names
        ProjectionMapProvider<A, String> fromProvider = new ProjectionMapProvider<>(query, getEntityClass());
        Path parent = fromProvider.getCriteriaParentProjection("parent", SearchJoinType.INNER);
        query.where(criteriaBuilder.equal(parent.get("actionTreeName"), baseActionTN)).
                select(fromProvider.getCriteriaParentProjection("actionTreeName"));
        TypedQuery<String> typedQuery = getTypedQuery(getEntityManager(), query);
        return typedQuery.getResultList();
    }
}
