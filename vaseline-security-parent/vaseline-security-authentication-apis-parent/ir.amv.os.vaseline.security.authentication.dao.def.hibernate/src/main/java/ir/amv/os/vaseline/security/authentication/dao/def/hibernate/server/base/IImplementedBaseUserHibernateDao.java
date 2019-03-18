package ir.amv.os.vaseline.security.authentication.dao.def.hibernate.server.base;

import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;
import org.hibernate.criterion.Restrictions;

/**
 * @author Amir
 */
public interface IImplementedBaseUserHibernateDao<U extends IBaseUserEntity>
        extends IBaseImplementedHibernateReadOnlyDao<U, Long>, IBaseUserDao<U> {

    @Override
    default U getUserByUsername(String username) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(), this, detachedCriteria -> {
            detachedCriteria.add(Restrictions.eq("username", username));
        }).unique();
    }
}
