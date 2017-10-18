package ir.amv.os.vaseline.data.jpa.apis.simplesearch.server.criteria;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.data.apis.dao.basic.server.from.IBaseCriteriaFromProvider;
import ir.amv.os.vaseline.data.apis.dao.basic.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.apis.search.simple.server.criteria.IBaseCriteriaSimpleSearchParser;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionInterruptException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBaseJpaSimpleSearchParser<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseCriteriaSimpleSearchParser<D, Id, CriteriaBuilder, Predicate, Path> {

    @Override
    default void addCriterionForProperty(CriteriaBuilder detachedCriteria, IBaseCriteriaFromProvider<Path> fromProvider, Map<String, Predicate> criterionListMap, String parentPTN, String propertyName, HashMap<String, Object> map, SearchJoinType joinType) throws InterceptionInterruptException {
        if (map.containsKey("id")) { // handle id first, in simple search the process will be interrupted
            IBaseCriteriaSimpleSearchParser.super.addCriterionForProperty(detachedCriteria, fromProvider, criterionListMap, parentPTN, "id", map, joinType);
            throw new InterceptionInterruptException(); // stop intercepting children
        }
        IBaseCriteriaSimpleSearchParser.super.addCriterionForProperty(detachedCriteria, fromProvider, criterionListMap, parentPTN, propertyName, map, joinType);
    }

    @Override
    default Predicate getPropertyCriterion(Path propertyAlias, Object propertyValue, CriteriaBuilder criteriaBuilder) throws InterceptionInterruptException {
        Predicate expression;
        if (propertyValue instanceof String) {
            expression = criteriaBuilder.like((Expression<String>)propertyAlias, "%" + propertyValue + "%");
        } else if (propertyValue instanceof Date) {
            Date date = (Date) propertyValue;
            expression = criteriaBuilder.between(propertyAlias, DateUtil.getDayStart(date),
                    DateUtil.getDayEnd(date));
        } else {
            expression = criteriaBuilder.equal(propertyAlias, propertyValue);
        }
        return expression;
    }

    @Override
    default SearchJoinType getJoinTypeFromExample(IBaseDto object) {
        return null;
    }

    @Override
    default Predicate andAll(CriteriaBuilder criteriaBuilder, List<Predicate> criterionList) {
        return criteriaBuilder.and(criterionList.toArray(new Predicate[0]));
    }

    @Override
    default Predicate orAll(CriteriaBuilder criteriaBuilder, List<Predicate> criterionList) {
        return criteriaBuilder.or(criterionList.toArray(new Predicate[0]));
    }
}