package ir.amv.os.vaseline.data.hibernate.apis.simplesearch.server.criteria;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.data.apis.dao.server.from.IBaseCriteriaFromProvider;
import ir.amv.os.vaseline.data.apis.dao.server.from.SearchJoinType;
import ir.amv.os.vaseline.data.apis.search.simple.server.criteria.IBaseCriteriaSimpleSearchParser;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionInterruptException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IBaseHibernateSimpleSearchParser<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseCriteriaSimpleSearchParser<D, Id, DetachedCriteria, Criterion, String> {

    @Override
    default void addCriterionForProperty(DetachedCriteria detachedCriteria, IBaseCriteriaFromProvider<String> fromProvider, Map<String, Criterion> criterionListMap, String parentPTN, String propertyName, HashMap<String, Object> map, SearchJoinType joinType) throws InterceptionInterruptException {
        if (map.containsKey("id")) { // handle id first, in simple search the process will be interrupted
            IBaseCriteriaSimpleSearchParser.super.addCriterionForProperty(detachedCriteria, fromProvider, criterionListMap, parentPTN, "id", map, joinType);
            throw new InterceptionInterruptException(); // stop intercepting children
        }
        IBaseCriteriaSimpleSearchParser.super.addCriterionForProperty(detachedCriteria, fromProvider, criterionListMap, parentPTN, propertyName, map, joinType);
    }

    @Override
    default Criterion getPropertyCriterion(String propertyAlias, Object propertyValue, DetachedCriteria criteriaBuilder) throws InterceptionInterruptException {
        Criterion expression;
        if (propertyValue instanceof String) {
            expression = Restrictions.like(propertyAlias, MatchMode.ANYWHERE);
        } else if (propertyValue instanceof Date) {
            Date date = (Date) propertyValue;
            expression = Restrictions.between(propertyAlias, DateUtil.getDayStart(date),
                    DateUtil.getDayEnd(date));
        } else {
            expression = Restrictions.eq(propertyAlias, propertyValue);
        }
        return expression;
    }

    @Override
    default SearchJoinType getJoinTypeFromExample(D object) {
        return null;
    }

    @Override
    default Criterion andAll(DetachedCriteria criteriaBuilder, List<Criterion> criterionList) {
        return Restrictions.and(criterionList.toArray(new Criterion[0]));
    }

    @Override
    default Criterion orAll(DetachedCriteria criteriaBuilder, List<Criterion> criterionList) {
        return Restrictions.or(criterionList.toArray(new Criterion[0]));
    }
}
