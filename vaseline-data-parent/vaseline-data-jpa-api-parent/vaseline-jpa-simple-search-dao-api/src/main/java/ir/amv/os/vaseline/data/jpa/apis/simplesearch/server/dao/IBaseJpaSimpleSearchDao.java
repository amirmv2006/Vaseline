package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.dao;

import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.data.apis.dao.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.apis.search.simple.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.IBaseJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor.JpaFetchProviderFacade;
import ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.criteria.IBaseJpaSimpleSearchParser;
import ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.criteria.defimpl.DefaultJpaSimpleSearchParserImpl;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IBaseJpaSimpleSearchDao<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseSimpleSearchDao<E, D, Id>,
        IBaseJpaReadOnlyDao<E, Id> {

    Class<D> getDtoClass();

    default IBaseJpaSimpleSearchParser<D, Id> getSimpleSearchExampleParser(D example) {
        return new DefaultJpaSimpleSearchParserImpl<>();
    }

    default JpaFetchProviderFacade<E, Id> exampleJpaFetchProviderFacade(D example) {
        return new JpaFetchProviderFacade<>(jpaFetchProvider(),this, (criteriaBuilder, query, fromProvider) -> {
            Predicate criterion = getSimpleSearchExampleParser(example).getCriteriaFromExampleRecursively(example, getDtoClass(), criteriaBuilder,
                    fromProvider, "");
            applyRootCondition(criteriaBuilder, fromProvider, query, criterion);
        });
    }

    // dao methods

    @Override
    default Long countByExample(D example) {
        return exampleJpaFetchProviderFacade(example).count();
    }

    @Override
    default List<E> searchByExample(D example) {
        return exampleJpaFetchProviderFacade(example).list();
    }

    @Override
    default IVaselineDataScroller<E> scrollByExample(D example) {
        return exampleJpaFetchProviderFacade(example).scroll();
    }

    @Override
    default List<E> searchByExample(final D example, PagingDto pagingDto) {
        return exampleJpaFetchProviderFacade(example).page(pagingDto);
    }
}
