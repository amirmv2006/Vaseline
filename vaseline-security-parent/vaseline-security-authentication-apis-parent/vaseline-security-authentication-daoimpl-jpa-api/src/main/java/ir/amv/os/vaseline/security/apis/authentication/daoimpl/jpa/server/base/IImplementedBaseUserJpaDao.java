package ir.amv.os.vaseline.security.apis.authentication.daoimpl.jpa.server.base;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseImplementedJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IImplementedBaseUserJpaDao<U extends IBaseUserEntity>
        extends IBaseImplementedJpaReadOnlyDao<U, Long>, IBaseUserDao<U> {

    @Override
    default U getUserByUsername(String username) {
        return new JpaFetchProviderFacade<U, Long>(jpaFetchProvider(), this, (criteriaBuilder, query, fromProvider) -> {
            applyRootCondition(criteriaBuilder, fromProvider, query, criteriaBuilder.equal(fromProvider
                    .getCriteriaParentProjection("username", null), username));
        }).unique();
    }
}
