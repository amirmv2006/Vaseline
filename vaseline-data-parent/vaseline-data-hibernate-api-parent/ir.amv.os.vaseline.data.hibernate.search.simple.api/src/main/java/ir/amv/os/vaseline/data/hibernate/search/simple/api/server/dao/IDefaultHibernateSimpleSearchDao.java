package ir.amv.os.vaseline.data.hibernate.search.simple.api.server.dao;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.hibernate.search.simple.api.server.criteria.IBaseHibernateSimpleSearchParser;
import ir.amv.os.vaseline.data.hibernate.search.simple.api.server.criteria.defimpl.DefaultHibernateSimpleSearchParserImpl;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.IDefaultHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IDefaultHibernateSimpleSearchDao<I extends Serializable, E extends IBaseEntity<I>, D extends IBaseDto<I>>
        extends IBaseSimpleSearchDao<I, E, D>,
        IDefaultHibernateReadOnlyDao<I, E> {

    default Class<D> getDtoClass() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(getClass());
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseDto.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<D>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    default IBaseHibernateSimpleSearchParser<I, D> getSimpleSearchExampleParser(D example) {
        return new DefaultHibernateSimpleSearchParserImpl<>();
    }

    default HibernateFetchProviderFacade<I, E> exampleHibernateFetchProviderFacade(D example) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(),this, detachedCriteria -> {
            Criterion criterion = getSimpleSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseDto.class, detachedCriteria,
                    new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria, getEntityClass()), "");
            if (criterion != null) {
                detachedCriteria.add(criterion);
            }
        });
    }

    // dao methods

    @Override
    default Long countByExample(D example) {
        return exampleHibernateFetchProviderFacade(example).count();
    }

    @Override
    default List<E> searchByExample(D example) {
        return exampleHibernateFetchProviderFacade(example).list();
    }

    @Override
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) {
        return exampleHibernateFetchProviderFacade(example).scroll(sortList);
    }

    @Override
    default List<E> searchByExample(final D example, PagingDto pagingDto) {
        return exampleHibernateFetchProviderFacade(example).page(pagingDto);
    }

}
