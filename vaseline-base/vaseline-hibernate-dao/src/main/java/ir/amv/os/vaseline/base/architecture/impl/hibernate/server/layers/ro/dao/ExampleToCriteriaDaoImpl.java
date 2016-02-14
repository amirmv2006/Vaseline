package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.dao.BaseDaoImpl;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.util.date.DateUtil;
import ir.amv.os.vaseline.base.core.shared.util.ds.KeyStartsWithMap;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionInterceptor;
import ir.amv.os.vaseline.base.core.shared.util.reflection.ReflectionUtil;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

public class ExampleToCriteriaDaoImpl<D>
		extends BaseDaoImpl {

	public ExampleToCriteriaDaoImpl() {
	}

	protected void pruneCriteriaBasedOnExampleRecursively(D example, final DetachedCriteria criteria, List<Criterion> criterionList,
			Map<String, String> aliasMap) {
		final HashMap<String, String> aliasMaps = new HashMap<String, String>();
		final HashMap<String, Criterion> restrictions = new HashMap<String, Criterion>();
		if (example == null) {
			return;
		}
		// no need for this anymore, since the object itself is caught by
		// interceptor too!
		// pruneCriteriaBasedOnExampleNonRecursive(example, criteria, "",
		// restrictions);
		ReflectionInterceptor<IBaseDto<?>> interceptor = new ReflectionInterceptor<IBaseDto<?>>() {
			@Override
			public IBaseDto<?> intercept(IBaseDto<?> object, String propertyTreeName) {
				String propertyRefined = propertyTreeName.replaceAll("\\.", "_");
				if (propertyTreeName.contains(".")) {
					String parentName = getParentFromPTN(propertyTreeName);
					String childName = getChildFromPTN(propertyTreeName);
					String property = parentName + "." + childName;
					aliasMaps.put(property.replaceAll("[0-9]", ""), propertyRefined.replaceAll("[0-9]", ""));
				} else if (!propertyTreeName.trim().equals("")) {
					aliasMaps.put(propertyTreeName.replaceAll("[0-9]", ""), propertyRefined.replaceAll("[0-9]", ""));
				}
				pruneCriteriaBasedOnExampleNonRecursive(object, criteria, propertyRefined, restrictions);
				return object;
			}

		};
		ReflectionUtil.intercept(example, IBaseDto.class, interceptor, "");
		doPruneCriteria(criteria, criterionList, aliasMap, restrictions, aliasMaps);
	}

	protected String getChildFromPTN(String propertyTreeName) {
		int dotIndex = propertyTreeName.lastIndexOf('.');
		String childName = propertyTreeName.substring(dotIndex + 1);
		return childName;
	}

	private String getParentTreeNameFromPTN(String propertyTreeName) {
		int dotIndex = propertyTreeName.lastIndexOf('.');
		String parentName = propertyTreeName.substring(0, dotIndex);
		return parentName;
	}

	protected String getParentFromPTN(String propertyTreeName) {
		return getParentTreeNameFromPTN(propertyTreeName).replaceAll("\\.", "_");
	}

	protected void doPruneCriteria(DetachedCriteria criteria, List<Criterion> criterionList, Map<String, String> aliasMap, Map<String, Criterion> restrictions,
			Map<String, String> aliasMaps) {
		Set<String> requiredParents = new HashSet<String>();
		for (String key : restrictions.keySet()) {
			if (key.contains(".")) {
				String[] split = key.split("\\.");
				String parentName = split[0];
				requiredParents.add(parentName.replaceAll("[0-9]", ""));
				while (parentName.contains("_")) {
					int underLineIndex = parentName.lastIndexOf('_');
					parentName = parentName.substring(0, underLineIndex);
					requiredParents.add(parentName.replaceAll("[0-9]", ""));
				}
			}
		}
		for (String parent : requiredParents) {
			for (String aliasKey : aliasMaps.keySet()) {
				if (aliasMaps.get(aliasKey).equals(parent)) {
					addAlias(aliasKey, parent, aliasMap, criteria, restrictions);
					break;
				}
			}
		}
		KeyStartsWithMap<Criterion> collectionsCriterion = new KeyStartsWithMap<Criterion>();
		for (String key : restrictions.keySet()) {
			if (hasNumber(key)) {
				collectionsCriterion.put(key, restrictions.get(key));
			} else {
				addRestrictionToCrieteria(criteria, criterionList, key, restrictions.get(key), restrictions);
			}
		}
		int size = collectionsCriterion.size();
		while (size > 1) {
			ArrayList<String> toBeRemoved = new ArrayList<String>();
			Map<String, Criterion> toBeAdded = new HashMap<String, Criterion>();
			for (String key : collectionsCriterion.keySet()) {
				if (key.contains(".")) {// g0_p0_r_s.name
					String[] split = key.split("\\.");
					String parentKey = split[0];// g0_p0_r_s
					// String propName = split[1];//name
					if (hasNumber(parentKey)) {
						int lastNumberIndex = lastNumberIndex(parentKey);
						String baseKey = parentKey.substring(0, lastNumberIndex + 1);// g0_p0
						List<String> keysByBaseKey = collectionsCriterion.getKeysByBaseKey(baseKey);
						boolean hasNumbericChild = false;
						for (String string : keysByBaseKey) {
							if (hasNumber(string.replaceFirst(baseKey, ""))) {
								hasNumbericChild = true;
								break;
							}
						}
						if (hasNumbericChild) {
							continue;
						}
						List<Criterion> criterionsList = collectionsCriterion.getValuesByBaseKey(baseKey);
						Criterion andAll = CriteriaUtil.andAll(criterionsList);
						toBeAdded.put(baseKey, andAll);
						toBeRemoved.addAll(keysByBaseKey);
					} else if (!hasNumber(parentKey)) {// g_p_r_s.name
						addRestrictionToCrieteria(criteria, criterionList, key, collectionsCriterion.get(key), restrictions);
						toBeRemoved.add(key);
					}
				} else {// g0_p0_r_s (happens in second loop)
					if (key.endsWith("0")) {
						int lastZeroIndex = key.lastIndexOf('0');
						String baseKey = key.substring(0, lastZeroIndex + 1);// g0_p0
						List<String> keysByBaseKey = collectionsCriterion.getKeysByBaseKey(baseKey);
						boolean hasNumbericChild = false;
						for (String string : keysByBaseKey) {
							if (hasNumber(string.replaceFirst(baseKey, ""))) {
								hasNumbericChild = true;
								break;
							}
						}
						if (hasNumbericChild) {
							continue;
						}
						String mainBaseKey = key.substring(0, lastZeroIndex);// g0_p
						List<Criterion> criterionsList = collectionsCriterion.getValuesByBaseKey(mainBaseKey);
						keysByBaseKey = collectionsCriterion.getKeysByBaseKey(mainBaseKey);
						Criterion orAll = CriteriaUtil.orAll(criterionsList);
						toBeAdded.put(mainBaseKey, orAll);
						toBeRemoved.addAll(keysByBaseKey);
					} else if (!hasNumber(key)) {// g_p_r_s
						addRestrictionToCrieteria(criteria, criterionList, key, collectionsCriterion.get(key), restrictions);
						toBeRemoved.add(key);
					}
				}
			}
			for (String string : toBeAdded.keySet()) {
				collectionsCriterion.put(string, toBeAdded.get(string));
			}
			for (String string : toBeRemoved) {
				collectionsCriterion.remove(string);
			}
			size = collectionsCriterion.size();
		}
		if (collectionsCriterion.size() > 0) {
			Criterion next = collectionsCriterion.values().iterator().next();
			addRestrictionToCrieteria(criteria, criterionList, collectionsCriterion.keySet().iterator().next(), next, restrictions);
		}
	}

	private void addAlias(String aliasKey, String alias, Map<String, String> aliasMap, DetachedCriteria criteria, Map<String, Criterion> restrictions) {
		if (!aliasMap.containsKey(aliasKey)) {
			// check if on of parents have id restriction
			if (aliasKey.contains(".")) {
				String[] split = aliasKey.split("\\.");
				String parentPath = split[0];
				boolean hasParentId = restrictions.get(parentPath + ".id") != null;
				while (!hasParentId && parentPath.contains("_")) {
					int lastUnderLineIndex = parentPath.lastIndexOf('_');
					parentPath = parentPath.substring(0, lastUnderLineIndex);
					hasParentId = restrictions.get(parentPath + ".id") != null;
				}
				if (!hasParentId && !restrictions.containsKey("id")) {
					criteria.createAlias(aliasKey, alias);
				}
			} else {
				if (!restrictions.containsKey("id")) {
					criteria.createAlias(aliasKey, alias);
				}
			}
		}
	}

	protected void addRestrictionToCrieteria(DetachedCriteria criteria, List<Criterion> criterionList, String key, Criterion criterion,
			Map<String, Criterion> restrictions) {
		if (!criterionList.contains(criterion)) {
			// check if on of parents have id restriction
			if (key.contains(".")) {
				String[] split = key.split("\\.");
				String parentPath = split[0];
				boolean hasParentId = false;
				while (!hasParentId && parentPath.contains("_")) {
					int lastUnderLineIndex = parentPath.lastIndexOf('_');
					parentPath = parentPath.substring(0, lastUnderLineIndex);
					hasParentId = restrictions.get(parentPath + ".id") != null;
				}
				if (!hasParentId) {
					criteria.add(criterion);
				}
			} else if (key.contains("_")) {
				String parentPath = key;
				boolean hasParentId = false;
				while (!hasParentId && parentPath.contains("_")) {
					int lastUnderLineIndex = parentPath.lastIndexOf('_');
					parentPath = parentPath.substring(0, lastUnderLineIndex);
					hasParentId = restrictions.get(parentPath + ".id") != null;
				}
				if (!hasParentId) {
					criteria.add(criterion);
				}
			} else {
				if (key.equals("id") || !restrictions.containsKey("id")) {
					criteria.add(criterion);
				}
			}
		}
	}

	protected int lastNumberIndex(String parentKey) {
		for (int i = parentKey.length() - 1; i >= 0; i--) {
			if (Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').contains(parentKey.charAt(i))) {
				while (Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').contains(parentKey.charAt(i))) {
					i--;
				}
				return i + 1;
			}
		}
		return -1;
	}

	private boolean hasNumber(String key) {
		// commented because of performance problems
		// return Pattern.compile("(.*)[0-9](.*)").matcher(key).matches();
		return key.contains("1") || key.contains("2") || key.contains("3") || key.contains("4") || key.contains("5") || key.contains("6") || key.contains("7")
				|| key.contains("8") || key.contains("9") || key.contains("0");
	}

	protected void pruneCriteriaBasedOnExampleNonRecursive(Object example, final DetachedCriteria criteria, String prefix, Map<String, Criterion> restrictions) {
		if (example == null) {
			return;
		}
		HashMap<String, Object> map = getMapFromObjectNonRecursive(example);
		if (map.containsKey("id")) {
			String idTreeName = prefix.equals("") ? "id" : prefix + ".id";
			Object id = map.get("id");
			SimpleExpression expression = Restrictions.eq(idTreeName.replaceAll("[0-9]", ""), id);
			restrictions.put(idTreeName, expression);
		} else {
			for (String key : map.keySet()) {
				String propertyTreeName = prefix.equals("") ? key : prefix + "." + key;
				Object object = map.get(key);
				Criterion expression = getCriterionForProperty(
						example, propertyTreeName, object);
				restrictions.put(propertyTreeName, expression);
			}
		}
	}

	protected Criterion getCriterionForProperty(Object example,
			String propertyTreeName, Object object) {
		Criterion expression;
		if (object instanceof String) {
			expression = Restrictions.like(propertyTreeName.replaceAll("[0-9]", ""), "%" + object + "%");
//		} else if (object instanceof SearchBetweenDatesServer) {
//			SearchBetweenDatesServer criteriaDate = (SearchBetweenDatesServer) object;
//			if (criteriaDate.getStartDate() != null && criteriaDate.getEndDate() != null) {
//				expression = Restrictions.between(propertyTreeName.replaceAll("[0-9]", ""), DateUtil.getDayStart(criteriaDate.getStartDate()),
//						DateUtil.getDayEnd(criteriaDate.getEndDate()));
//			} else if (criteriaDate.getStartDate() != null) {
//				expression = Restrictions.ge(propertyTreeName.replaceAll("[0-9]", ""), DateUtil.getDayStart(criteriaDate.getStartDate()));
//			} else {
//				expression = Restrictions.le(propertyTreeName.replaceAll("[0-9]", ""), DateUtil.getDayEnd(criteriaDate.getEndDate()));
//			}
		} else if (object instanceof Date) {
			Date date = (Date) object;
			expression = Restrictions.between(propertyTreeName.replaceAll("[0-9]", ""), DateUtil.getDayStart(date),
					DateUtil.getDayEnd(date));
		} else {
			expression = Restrictions.eq(propertyTreeName.replaceAll("[0-9]", ""), object);
		}
		return expression;
	}

	/**
	 * put an object to a Map
	 * 
	 * @param example
	 * @return
	 */
	protected HashMap<String, Object> getMapFromObjectNonRecursive(Object example) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(example.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
				checkPropertyForClass(example, result, propertyDescriptor, Number.class);
				checkPropertyForClass(example, result, propertyDescriptor, Date.class);
				checkPropertyForClass(example, result, propertyDescriptor, String.class);
				checkPropertyForClass(example, result, propertyDescriptor, Boolean.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * searches a property descriptor for query class and puts its value on the
	 * result map if found
	 * 
	 * @param example
	 * @param result
	 * @param propertyDescriptor
	 * @param query
	 */
	protected void checkPropertyForClass(Object example, HashMap<String, Object> result, PropertyDescriptor propertyDescriptor, Class<?> query) {
		if (propertyDescriptor.getWriteMethod() == null) {
			return;
		}
		Method readMethod = propertyDescriptor.getReadMethod();
		//FIXME getId method
		if (readMethod.getReturnType().equals(Serializable.class)) {
			try {
				Object invoke = readMethod.invoke(example);
				if (invoke != null) {
					result.put(propertyDescriptor.getName(), invoke);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (readMethod.isBridge()) {
			try {
				readMethod = example.getClass().getMethod(readMethod.getName(), readMethod.getParameterTypes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Class<?> searchResult = ReflectionUtil.searchMethodReturnType(readMethod, query);
		if (searchResult != null) {
			Object value = ReflectionUtil.getMethodResultAsArray(example, readMethod, query)[0];
			if (value != null) {
				result.put(propertyDescriptor.getName(), value);
			}
		}
		searchResult = ReflectionUtil.searchMethodReturnTypeForEnum(readMethod);
		if (searchResult != null) {
			Object value = ReflectionUtil.getMethodResultAsArray(example, readMethod, searchResult)[0];
			if (value != null) {
				result.put(propertyDescriptor.getName(), value);
			}
		}
	}

	protected void createAliasForPTNIfNecessory(String propertyTreeNames, Map<String, String> aliasMap) {
		if (propertyTreeNames.contains(".")) { // a.b.c.d
			String parentTreeNameFromPTN = getParentTreeNameFromPTN(propertyTreeNames); // a.b.c
			if (parentTreeNameFromPTN.contains(".")) {
				String parentFromPTN = getParentFromPTN(parentTreeNameFromPTN); // a_b
				String childFromPTN = getChildFromPTN(parentTreeNameFromPTN); // c
				aliasMap.put(parentFromPTN + "." + childFromPTN, parentTreeNameFromPTN.replaceAll("\\.", "_"));
			} else {
				aliasMap.put(parentTreeNameFromPTN, parentTreeNameFromPTN);
			}
		}
	}

}
