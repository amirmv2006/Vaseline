package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.criteriaabstractor;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.projection.IJpaCriteriaFromProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@FunctionalInterface
public interface IJpaCriteriaPrunerFunctionalInterface<QueryResult> {

    void pruneCriteria(CriteriaBuilder criteriaBuilder, CriteriaQuery<QueryResult> query, IJpaCriteriaFromProvider fromProvider);
}
