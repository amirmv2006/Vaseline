package ir.amv.os.vaseline.data.jpa.dao.basic.api.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@FunctionalInterface
public interface IJpaCriteriaPrunerFunctionalInterface<QueryResult> {

    void pruneCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<QueryResult> query, IJpaCriteriaFromProvider fromProvider);
}
