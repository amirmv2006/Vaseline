package ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.user;

import ir.amv.os.vaseline.data.apis.dao.basic.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.dao.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.model.server.user.ISecurityUserWithRoleEntity;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public interface IImplementedSecurityUserJpaDao<U extends ISecurityUserWithRoleEntity<?>>
        extends IBaseImplementedJpaReadOnlyDao<U, Long>,
        ISecurityUserDao<U> {

    @Override
    default List<String> getUserActionTreeNames(String username) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<String> query = createQuery(criteriaBuilder, String.class); // querying action tree names
        ProjectionMapProvider<U, String> fromProvider = new ProjectionMapProvider<>(query, getEntityClass());
        Path action = fromProvider.getCriteriaParentProjection("roles.actions", SearchJoinType.INNER);
        query.where(criteriaBuilder.equal(fromProvider.getCriteriaParentProjection("username"), username))
                .select(action.get("actionTreeName"));
        TypedQuery<String> typedQuery = getTypedQuery(getEntityManager(), query);
        return typedQuery.getResultList();
    }

    @Override
    default List<String> getUsernamesWithAccessToActionTreeName(final String... actionTreeNames) {
        if (actionTreeNames == null) {
            return null;
        }
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<String> query = createQuery(criteriaBuilder, String.class); // querying action tree names
        ProjectionMapProvider<U, String> fromProvider = new ProjectionMapProvider<>(query, getEntityClass());
        Path action = fromProvider.getCriteriaParentProjection("roles.actions", SearchJoinType.INNER);
        List<Predicate> actionTreeNameConds = new ArrayList<>();
        for (String actionTreeName : actionTreeNames) {
            actionTreeNameConds.add(criteriaBuilder.equal(action.get("actionTreeName"), actionTreeName));
        }
        query.where(criteriaBuilder.or(actionTreeNameConds.toArray(new Predicate[0])))
                .select(fromProvider.getCriteriaParentProjection("username"));
        TypedQuery<String> typedQuery = getTypedQuery(getEntityManager(), query);
        return typedQuery.getResultList();
    }
}
