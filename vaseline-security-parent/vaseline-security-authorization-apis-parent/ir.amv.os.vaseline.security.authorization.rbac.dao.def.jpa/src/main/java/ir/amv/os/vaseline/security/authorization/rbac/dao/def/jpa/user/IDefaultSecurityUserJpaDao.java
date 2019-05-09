package ir.amv.os.vaseline.security.authorization.rbac.dao.def.jpa.user;

import ir.amv.os.vaseline.data.dao.basic.api.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.ProjectionMapProvider;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;
import ir.amv.os.vaseline.security.authorization.rbac.dao.api.user.ISecurityUserDao;
import ir.amv.os.vaseline.security.authorization.rbac.model.api.user.ISecurityUserWithRoleEntity;

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
public interface IDefaultSecurityUserJpaDao<U extends ISecurityUserWithRoleEntity<?>>
        extends IDefaultJpaReadOnlyDao<Long, U>,
        ISecurityUserDao<U> {

    @Override
    default List<String> getUserActionTreeNames(String username) {
        CriteriaBuilder criteriaBuilder = createCriteriaBuilder();
        CriteriaQuery<String> query = createQuery(criteriaBuilder, String.class); // querying action tree names
        ProjectionMapProvider<U, String> fromProvider = new ProjectionMapProvider<>(query, (Class<U>)getEntityClass());
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
        ProjectionMapProvider<U, String> fromProvider = new ProjectionMapProvider<>(query, (Class<U>)getEntityClass());
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
