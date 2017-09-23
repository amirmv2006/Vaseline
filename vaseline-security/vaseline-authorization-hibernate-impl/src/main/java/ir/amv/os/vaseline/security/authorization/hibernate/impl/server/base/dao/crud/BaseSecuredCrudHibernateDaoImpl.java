package ir.amv.os.vaseline.security.authorization.hibernate.impl.server.base.dao.crud;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.crud.dao.BaseCrudHibernateDaoImpl;
import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.api.shared.util.callback.IBaseReturningCallback;
import ir.amv.os.vaseline.security.authorization.api.shared.base.dao.crud.IBaseSecuredCrudDao;
import ir.amv.os.vaseline.security.authorization.hibernate.impl.server.criteria.HibernateSecurityCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/28/2016.
 */
public class BaseSecuredCrudHibernateDaoImpl<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable, SecurityCriteria extends HibernateSecurityCriteria>
        extends BaseCrudHibernateDaoImpl<E, D, Id>
        implements IBaseSecuredCrudDao<E, D, Id, SecurityCriteria> {
    @Override
    public E getById(Id id, SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        detCriteria.add(Restrictions.idEq(id));
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        E uniqueResult = getEntityFromCriteria(criteria);
        return uniqueResult;
    }

    @Override
    public E getByIdDetached(Id id, SecurityCriteria securityCriteria) {
        E byId = getById(id, securityCriteria);
        Session currentSession = getCurrentSession();
        currentSession.evict(byId);
        return byId;
    }

    @Override
    public Long countAll(SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        detCriteria = getCountCriteria(detCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return getCountResult(criteria);
    }

    @Override
    public List<E> getAll(SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return getListFromCriteria(criteria);
    }

    @Override
    public IVaselineDataScroller<E> scrollAll(SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return scrollCriteria(criteria);
    }

    @Override
    public List<E> getAll(PagingDto pagingDto, final SecurityCriteria securityCriteria) {
        List<E> listFromCriteria = pagingResultCreator.getPagingResult(this, new IBaseReturningCallback<DetachedCriteria>() {
            @Override
            public DetachedCriteria execute() {
                return createCriteria(securityCriteria);
            }
        }, pagingDto);
        return listFromCriteria;
    }

    @Override
    public Long countByExample(D example, SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        pruneCriteriaBasedOnExampleRecursively(example, detCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        detCriteria = getCountCriteria(detCriteria);
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        Long countResult = getCountResult(criteria);
        return countResult;
    }

    @Override
    public List<E> searchByExample(D example, SecurityCriteria securityCriteria) {
        DetachedCriteria detachedCriteria = createCriteria(securityCriteria);
        pruneCriteriaBasedOnExampleRecursively(example, detachedCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        Criteria criteria = getCriteriaFromDetachedCriteria(detachedCriteria);
        List<E> listFromCriteria = getListFromCriteria(criteria);
        return listFromCriteria;
    }

    @Override
    public IVaselineDataScroller<E> scrollByExample(D example, SecurityCriteria securityCriteria) {
        DetachedCriteria detCriteria = createCriteria(securityCriteria);
        pruneCriteriaBasedOnExampleRecursively(example, detCriteria,
                new ArrayList<Criterion>(), new HashMap<String, String>());
        Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
        return scrollCriteria(criteria);
    }

    @Override
    public List<E> searchByExample(final D example, PagingDto pagingDto, final SecurityCriteria securityCriteria) {
        List<E> listFromCriteria = pagingResultCreator.getPagingResult(this, new IBaseReturningCallback<DetachedCriteria>() {
            @Override
            public DetachedCriteria execute() {
                DetachedCriteria detachedCriteria = createCriteria(securityCriteria);
                pruneCriteriaBasedOnExampleRecursively(example, detachedCriteria,
                        new ArrayList<Criterion>(), new HashMap<String, String>());
                return detachedCriteria;
            }
        }, pagingDto);
        return listFromCriteria;
    }

    // BASE METHODS
    protected DetachedCriteria createCriteria(SecurityCriteria securityCriteria) {
        DetachedCriteria criteria = createCriteria();
        if (securityCriteria != null) {
            List<Criterion> restrictionsList = securityCriteria.getRestrictions();
            if (restrictionsList != null) {
                for (Criterion criterion : restrictionsList) {
                    criteria.add(criterion);
                }
            }
            Map<String, String> aliasMap = securityCriteria.getAliasMap();
            if (aliasMap != null) {
                for (String aliasKey : aliasMap.keySet()) {
                    criteria.createAlias(aliasKey, aliasMap.get(aliasKey));
                }
            }
        }
        return criteria;
    }
}
