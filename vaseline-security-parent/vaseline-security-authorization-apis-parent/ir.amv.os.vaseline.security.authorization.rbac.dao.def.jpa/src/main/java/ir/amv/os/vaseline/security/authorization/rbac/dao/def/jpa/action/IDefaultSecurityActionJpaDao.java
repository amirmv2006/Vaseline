package ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.action;

import ir.amv.os.vaseline.data.dao.basic.api.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.action.ISecurityActionDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.action.ISecurityAction;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import java.util.List;

/**
 * @author Amir
 */
public interface IDefaultSecurityActionJpaDao<A extends ISecurityAction<A>>
        extends IDefaultJpaReadOnlyDao<A, Long>,
        ISecurityActionDao<A> {

    @Override
    default List<String> getActionChildTreeNames(final String baseActionTN) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<String> query = createQuery(criteriaBuilder, String.class); // querying action tree names
        ProjectionMapProvider<A, String> fromProvider = new ProjectionMapProvider<>(query, (Class<A>)getEntityClass());
        Path parent = fromProvider.getCriteriaParentProjection("parent", SearchJoinType.INNER);
        query.where(criteriaBuilder.equal(parent.get("actionTreeName"), baseActionTN)).
                select(fromProvider.getCriteriaParentProjection("actionTreeName"));
        TypedQuery<String> typedQuery = getTypedQuery(getEntityManager(), query);
        return typedQuery.getResultList();
    }
}
