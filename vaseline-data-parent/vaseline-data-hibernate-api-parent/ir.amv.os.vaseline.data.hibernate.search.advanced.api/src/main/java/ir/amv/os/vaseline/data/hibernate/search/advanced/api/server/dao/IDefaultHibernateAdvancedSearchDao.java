package ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.dao;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.criteria.IBaseHibernateAdvancedSearchParser;
import ir.amv.os.vaseline.data.hibernate.search.advanced.api.server.criteria.defimpl.DefaultHibernateAdvancedSearchParserImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IDefaultHibernateAdvancedSearchDao<Id extends Serializable, E extends IBaseBusinessModel<Id>, SO extends  IBaseSearchObject>
        extends IBaseAdvancedSearchRepository<Id, E, SO>,
        IDefaultHibernateReadOnlyDao<Id, E> {

    default Class<SO> getSearchObjectClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(getClass());
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseSearchObject.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<SO>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    default IBaseHibernateAdvancedSearchParser<SO> getAdvancedSearchExampleParser(SO example) {
        return new DefaultHibernateAdvancedSearchParserImpl<>();
    }

    default HibernateFetchProviderFacade<Id, E> advancedSearchHibernateFetchProviderFacade(SO example) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(),this, detachedCriteria -> {
            Criterion criterion = getAdvancedSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, detachedCriteria,
                    new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria, getEntityClass()), "");
            if (criterion != null) {
                detachedCriteria.add(criterion);
            }
        });
    }

    // dao methods

    @Override
    default Long countBySearchObject(SO example) {
        return advancedSearchHibernateFetchProviderFacade(example).count();
    }

    @Override
    default List<E> searchBySearchObject(SO example) {
        return advancedSearchHibernateFetchProviderFacade(example).list();
    }

    @Override
    default IVaselineDataScroller<E> scrollBySearchObject(SO example, List<SortDto> sortList) {
        return advancedSearchHibernateFetchProviderFacade(example).scroll(sortList);
    }

    @Override
    default List<E> searchBySearchObject(final SO example, PagingDto pagingDto) {
        return advancedSearchHibernateFetchProviderFacade(example).page(pagingDto);
    }
}
