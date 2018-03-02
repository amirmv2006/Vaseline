package ir.amv.os.vaseline.data.hibernate.apis.dao.server.ro.criteriaabstractor;

import org.hibernate.criterion.DetachedCriteria;

@FunctionalInterface
public interface IDetachedCriteriaPrunerFunctionalInterface {

    void pruneCriteria(DetachedCriteria detachedCriteria);
}
