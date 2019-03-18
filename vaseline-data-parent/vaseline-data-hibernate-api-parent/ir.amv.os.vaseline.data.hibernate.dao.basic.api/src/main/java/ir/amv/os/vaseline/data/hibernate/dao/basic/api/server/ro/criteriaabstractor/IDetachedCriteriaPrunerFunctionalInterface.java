package ir.amv.os.vaseline.data.hibernate.dao.basic.api.server.ro.criteriaabstractor;

import org.hibernate.criterion.DetachedCriteria;

@FunctionalInterface
public interface IDetachedCriteriaPrunerFunctionalInterface {

    void pruneCriteria(DetachedCriteria detachedCriteria);
}
