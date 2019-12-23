package ir.amv.os.vaseline.security.authentication.dao.def.jpa.server.base;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserBusinessModel;

/**
 * @author Amir
 */
public interface IDefaultBaseUserJpaDao<U extends IBaseUserBusinessModel>
        extends IDefaultJpaReadOnlyRepository<Long, U>, IBaseUserDao<U> {

    @Override
    default U getUserByUsername(String username) {
        return new JpaFetchProviderFacade<Long, U>(jpaFetchProvider(), this, (criteriaBuilder, query, fromProvider) -> {
            applyRootCondition(criteriaBuilder, fromProvider, query, criteriaBuilder.equal(fromProvider
                    .getCriteriaParentProjection("username", null), username));
        }).unique();
    }
}
