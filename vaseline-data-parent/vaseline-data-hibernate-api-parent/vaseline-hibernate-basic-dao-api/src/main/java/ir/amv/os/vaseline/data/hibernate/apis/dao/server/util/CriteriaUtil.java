package ir.amv.os.vaseline.data.hibernate.apis.dao.server.util;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CriteriaUtil {

	public static Criterion andAll(List<Criterion> criterionsList) {
		if (criterionsList.isEmpty()) {
			return null;
		}
		Criterion criterion = criterionsList.get(0);
		if (criterionsList.size() == 1) {
			return criterion;
		}
		for (int i = 1; i < criterionsList.size(); i++) {
			criterion = Restrictions.and(criterion, criterionsList.get(i));
		}
		return criterion;
	}
	
	public static Criterion orAll(List<Criterion> criterionsList) {
		if (criterionsList.isEmpty()) {
			return null;
		}
		Criterion criterion = criterionsList.get(0);
		if (criterionsList.size() == 1) {
			return criterion;
		}
		for (int i = 1; i < criterionsList.size(); i++) {
			criterion = Restrictions.or(criterion, criterionsList.get(i));
		}
		return criterion;
	}
}
