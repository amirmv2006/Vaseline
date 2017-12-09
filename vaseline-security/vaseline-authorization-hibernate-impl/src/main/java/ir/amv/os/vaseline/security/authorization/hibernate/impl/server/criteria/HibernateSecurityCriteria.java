package ir.amv.os.vaseline.security.authorization.hibernate.impl.server.criteria;

import ir.amv.os.vaseline.data.hibernate.apis.dao.server.crud.CriteriaUtil;
import ir.amv.os.vaseline.security.apis.authorization.basic.server.criteria.ISecurityCriteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/27/2016.
 */
public class HibernateSecurityCriteria
        implements ISecurityCriteria {

    private Map<String, String> aliasMap;
    private List<Criterion> restrictions;

    public HibernateSecurityCriteria(Map<String, String> aliasMap, Criterion... restrictions) {
        this.aliasMap = aliasMap;
        if (restrictions != null) {
            this.restrictions = new ArrayList<Criterion>(Arrays.asList(restrictions));
        }
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
