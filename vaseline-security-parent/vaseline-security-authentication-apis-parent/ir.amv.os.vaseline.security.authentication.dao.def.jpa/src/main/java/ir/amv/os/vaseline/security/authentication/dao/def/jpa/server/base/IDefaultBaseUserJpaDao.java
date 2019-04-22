package ir.amv.os.vaseline.security.authentication.dao.def.jpa.server.base;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;

/**
 * @author Amir
 */
public interface IDefaultBaseUserJpaDao<U extends IBaseUserEntity>
        extends IDefaultJpaReadOnlyDao<U, Long>, IBaseUserDao<U> {

    @Override
    default U getUserByUsername(String username) {
        return new JpaFetchProviderFacade<U, Long>(jpaFetchProvider(), this, (criteriaBuilder, query, fromProvider) -> {
            applyRootCondition(criteriaBuilder, fromProvider, query, criteriaBuilder.equal(fromProvider
                    .getCriteriaParentProjection("username", null), username));
        }).unique();
    }
}
