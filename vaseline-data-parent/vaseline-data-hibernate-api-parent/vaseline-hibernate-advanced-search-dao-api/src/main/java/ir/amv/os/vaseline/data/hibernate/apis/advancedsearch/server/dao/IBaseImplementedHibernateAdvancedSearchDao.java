package ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.criteria.IBaseHibernateAdvancedSearchParser;
import ir.amv.os.vaseline.data.hibernate.apis.advancedsearch.server.criteria.defimpl.DefaultHibernateAdvancedSearchParserImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.projection.DefaultHibernateCriteriaProjectionProviderImpl;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.IBaseImplementedHibernateReadOnlyDao;
import ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor.HibernateFetchProviderFacade;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

public interface IBaseImplementedHibernateAdvancedSearchDao<E extends IBaseEntity<Id>, SO extends  IBaseSearchObject, Id extends Serializable>
        extends IBaseAdvancedSearchDao<E, SO, Id>,
        IBaseImplementedHibernateReadOnlyDao<E, Id> {

    Class<SO> getSearchObjectClass();

    default IBaseHibernateAdvancedSearchParser<SO> getAdvancedSearchExampleParser(SO example) {
        return new DefaultHibernateAdvancedSearchParserImpl<>();
    }

    default HibernateFetchProviderFacade<E, Id> advancedSearchHibernateFetchProviderFacade(SO example) {
        return new HibernateFetchProviderFacade<>(hibernateFetchProvider(),this, detachedCriteria -> {
            Criterion criterion = getAdvancedSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, detachedCriteria,
                    new DefaultHibernateCriteriaProjectionProviderImpl(detachedCriteria), "");
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
