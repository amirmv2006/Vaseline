package ir.amv.os.vaseline.security.authorization.hibernate.impl.server.criteria;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao.CriteriaUtil;
import ir.amv.os.vaseline.security.authorization.api.shared.criteria.ISecurityCriteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.*;

/**
 * Created by AMV on 2/27/2016.
 */
public class HibernateSecurityCriteria
        implements ISecurityCriteria {

    private Map<String, String> aliasMap;
    private List<Criterion> restrictions;

    public HibernateSecurityCriteria(Map<String, String> aliasMap, Criterion... restrictions) {
        this.aliasMap = aliasMap;
        this.restrictions = new ArrayList<Criterion>(Arrays.asList(restrictions));
    }

    public Map<String, String> getAliasMap() {
        return aliasMap;
    }

    public void setAliasMap(Map<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    public List<Criterion> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Criterion> restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public ISecurityCriteria or(ISecurityCriteria criteria) {
        if (criteria instanceof HibernateSecurityCriteria) {
            HibernateSecurityCriteria hibernateSecurityCriteria = (HibernateSecurityCriteria) criteria;
            Map<String, String> aliasMapCopy = new HashMap<String, String>(hibernateSecurityCriteria.aliasMap);
            aliasMapCopy.putAll(aliasMap);
            Criterion otherCriterion = CriteriaUtil.andAll(hibernateSecurityCriteria.restrictions);
            Criterion myCriterion = CriteriaUtil.andAll(restrictions);
            return new HibernateSecurityCriteria(aliasMapCopy, Restrictions.or(myCriterion, otherCriterion));
        }
        return null;
    }
}
