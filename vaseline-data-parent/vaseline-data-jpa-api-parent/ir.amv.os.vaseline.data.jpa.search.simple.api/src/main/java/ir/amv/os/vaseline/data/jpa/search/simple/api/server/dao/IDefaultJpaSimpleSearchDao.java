package ir.amv.os.vaseline.data.jpa.search.simple.api.server.dao;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.sort.SortDto;
import ir.amv.os.vaseline.data.dao.basic.api.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.search.simple.api.server.criteria.IBaseJpaSimpleSearchParser;
import ir.amv.os.vaseline.data.jpa.search.simple.api.server.criteria.defimpl.DefaultJpaSimpleSearchParserImpl;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.IDefaultJpaReadOnlyDao;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor.JpaFetchProviderFacade;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public interface IDefaultJpaSimpleSearchDao<Id extends Serializable, E extends IBaseEntity<Id>, D extends IBaseDto<Id>>
        extends IBaseSimpleSearchDao<Id, E, D>,
        IDefaultJpaReadOnlyDao<Id, E> {

    default IBaseJpaSimpleSearchParser<Id, D> getSimpleSearchExampleParser(D example) {
        return new DefaultJpaSimpleSearchParserImpl<>();
    }

    default JpaFetchProviderFacade<Id, E> exampleJpaFetchProviderFacade(D example) {
        return new JpaFetchProviderFacade<>(jpaFetchProvider(),this, (criteriaBuilder, query, fromProvider) -> {
            Predicate criterion = getSimpleSearchExampleParser(example).getCriteriaFromExampleRecursively(example, IBaseDto.class, criteriaBuilder,
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
    default IVaselineDataScroller<E> scrollByExample(D example, List<SortDto> sortList) {
        return exampleJpaFetchProviderFacade(example).scroll(sortList);
    }

    @Override
    default List<E> searchByExample(final D example, PagingDto pagingDto) {
        return exampleJpaFetchProviderFacade(example).page(pagingDto);
    }
}
