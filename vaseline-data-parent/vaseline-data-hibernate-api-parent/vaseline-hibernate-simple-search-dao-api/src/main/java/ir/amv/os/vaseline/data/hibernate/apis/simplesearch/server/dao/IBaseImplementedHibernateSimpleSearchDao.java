package ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.criteria.IBaseHibernateSimpleSearchParser;
import ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.criteria.defimpl.DefaultHibernateSimpleSearchParserImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedHibernateSimpleSearchDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseSimpleSearchDao<E, D, Id>,
        IBaseImplementedHibernateReadOnlyDao<E, Id> {

    Class<D> getDtoClass();

    default IBaseHibernateSimpleSearchParser<D, Id> getSimpleSearchExampleParser(D example) {
        return new DefaultHibernateSimpleSearchParserImpl<>();
    }

    default HibernateFetchProviderFacade<E, Id> exampleHibernateFetchProviderFacade(D example) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(),this, detachedCriteria -> {
            Criterion criterion = getSimpleSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseDto.class, detachedCriteria,
                    new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria), "");
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
