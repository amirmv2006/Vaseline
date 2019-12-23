package ir.amv.os.vaseline.data.jpa.search.advanced.api.server.dao;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.search.advanced.api.server.criteria.IBaseJpaAdvancedSearchParser;
import ir.amv.os.vaseline.data.jpa.search.advanced.api.server.criteria.defimpl.DefaultJpaAdvancedSearchParserImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyRepository;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IDefaultJpaAdvancedSearchDao<I extends Serializable, E extends IBaseBusinessModel<I>, SO extends  IBaseSearchObject>
        extends IBaseAdvancedSearchRepository<I, E, SO>,
        IDefaultJpaReadOnlyRepository<I, E> {

    default IBaseJpaAdvancedSearchParser<SO> getAdvancedSearchExampleParser(SO example) {
        return new DefaultJpaAdvancedSearchParserImpl<>();
    }

    default JpaFetchProviderFacade<I, E> advancedSearchJpaFetchProviderFacade(SO example) {
        return new JpaFetchProviderFacade<>(jpaFetchProvider(),this, (criteriaBuilder, query, fromProvider) -> {
            Predicate criterion = getAdvancedSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseSearchObject.class, criteriaBuilder,
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
    default IVaselineDataScroller<E> scrollBySearchObject(SO example, List<SortDto> sortList) {
        return advancedSearchJpaFetchProviderFacade(example).scroll(sortList);
    }

    @Override
    default List<E> searchBySearchObject(final SO example, PagingDto pagingDto) {
        return advancedSearchJpaFetchProviderFacade(example).page(pagingDto);
    }
}
