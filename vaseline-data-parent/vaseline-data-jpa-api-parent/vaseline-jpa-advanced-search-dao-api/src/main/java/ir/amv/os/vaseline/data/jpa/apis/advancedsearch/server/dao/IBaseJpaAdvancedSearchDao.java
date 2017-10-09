package ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.advanced.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.apis.search.advanced.server.ro.IBaseAdvancedSearchDao;
import ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.criteria.IBaseJpaAdvancedSearchParser;
import ir.amv.os.vaseline.data.jpa.apis.advancedsearch.server.criteria.defimpl.DefaultJpaAdvancedSearchParserImpl;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IBaseJpaAdvancedSearchDao<E extends IBaseEntity<Id>, SO extends  IBaseSearchObject, Id extends Serializable>
        extends IBaseAdvancedSearchDao<E, SO>,
        IBaseJpaReadOnlyDao<E, Id> {

    Class<SO> getSearchObjectClass();

    default IBaseJpaAdvancedSearchParser<SO> getAdvancedSearchExampleParser(SO example) {
        return new DefaultJpaAdvancedSearchParserImpl<>();
    }

    default JpaFetchProviderFacade<E, Id> advancedSearchJpaFetchProviderFacade(SO example) {
        return new JpaFetchProviderFacade<>(jpaFetchProvider(),this, (criteriaBuilder, query, fromProvider) -> {
            Predicate criterion = getAdvancedSearchExampleParser(example).getCriteriaFromExampleRecursively(example, getSearchObjectClass(), criteriaBuilder,
                    fromProvider, "");
            applyRootCondition(criteriaBuilder, fromProvider, query, criterion);
        });
    }

    // dao methods

    @Override
    default Long countBySearchObject(SO example) {
        return advancedSearchJpaFetchProviderFacade(example).count();
    }

    @Override
    default List<E> searchBySearchObject(SO example) {
        return advancedSearchJpaFetchProviderFacade(example).list();
    }

    @Override
    default IVaselineDataScroller<E> scrollBySearchObject(SO example) {
        return advancedSearchJpaFetchProviderFacade(example).scroll();
    }

    @Override
    default List<E> searchBySearchObject(final SO example, PagingDto pagingDto) {
        return advancedSearchJpaFetchProviderFacade(example).page(pagingDto);
    }
}
