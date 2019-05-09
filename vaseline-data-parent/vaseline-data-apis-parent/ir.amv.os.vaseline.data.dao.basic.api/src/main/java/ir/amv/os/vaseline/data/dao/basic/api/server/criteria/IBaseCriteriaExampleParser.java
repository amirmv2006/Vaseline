package ir.amv.os.vaseline.data.dao.basic.api.server.criteria;

import ir.amv.os.vaseline.basics.core.api.shared.util.ds.KeyStartsWithMap;
import ir.amv.os.vaseline.data.dao.basic.api.server.from.IBaseCriteriaFromProvider;
import ir.amv.os.vaseline.data.dao.basic.api.server.from.SearchJoinType;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionInterceptor;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionInterruptException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * abstracting search by Dto as Example or SearchObject, also by JPA or Hibernate Criteria
 * @param <Example>
 * @param <CriteriaBuilder>
 * @param <Criterion>
 * @param <Projection>
 */
public interface IBaseCriteriaExampleParser<BaseExample, Example, CriteriaBuilder, Criterion, Projection> {

    SearchJoinType getJoinTypeFromExample(BaseExample object);
    Criterion getPropertyCriterion(Projection propertyAlias, Object propertyValue, CriteriaBuilder criteriaBuilder) throws InterceptionInterruptException;
    Criterion andAll(CriteriaBuilder criteriaBuilder, List<Criterion> criterionList);
    Criterion orAll(CriteriaBuilder criteriaBuilder, List<Criterion> criterionList);

    default Criterion getCriteriaFromExampleRecursively(Example example, Class<BaseExample> queryClass, CriteriaBuilder criteriaBuilder, IBaseCriteriaFromProvider<Projection> fromProvider, String prefix) {
        if (example == null) {
            return null;
        }
        final Map<String, Criterion> criterionListMap = new HashMap<>();
        ReflectionInterceptor<BaseExample> interceptor = (object, propertyTreeName) -> {
            if (object != null) {
                HashMap<String, Object> map = getMapFromObjectNonRecursive(object);
                removeUnnecessaryKeysFromObjectMap(map);
                SearchJoinType joinType = getJoinTypeFromExample(object);
                for (String propertyName : map.keySet()) {
                    addCriterionForProperty(criteriaBuilder, fromProvider, criterionListMap, propertyTreeName, propertyName, map, joinType);
                }
            }
            return object;
        };
        ReflectionUtil.intercept(example, queryClass, interceptor, prefix);
        return consolidatedCriterion(criteriaBuilder, criterionListMap, fromProvider);
    }

    default void removeUnnecessaryKeysFromObjectMap(HashMap<String, Object> map) {
    }

    default Criterion consolidatedCriterion(CriteriaBuilder criteriaBuilder, Map<String, Criterion> criterionListMap, IBaseCriteriaFromProvider<Projection> fromProvider) {
        KeyStartsWithMap<Criterion> collectionsCriterion = new KeyStartsWithMap<>();
        List<Criterion> criterionList = new ArrayList<>();
        for (String key : criterionListMap.keySet()) {
            if (hasNumber(key)) {
                collectionsCriterion.put(key, criterionListMap.get(key));
            } else {
                criterionList.add(criterionListMap.get(key));
            }
        }
        int size = collectionsCriterion.size();
        while (size > 1) {
            ArrayList<String> toBeRemoved = new ArrayList<String>();
            Map<String, Criterion> toBeAdded = new HashMap<>();
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
                        Criterion andAll = andAll(criteriaBuilder, criterionsList);
                        toBeAdded.put(baseKey, andAll);
                        toBeRemoved.addAll(keysByBaseKey);
                    } else if (!hasNumber(parentKey)) {// g_p_r_s.name
                        criterionList.add(collectionsCriterion.get(key));
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
                        Criterion orAll = orAll(criteriaBuilder, criterionsList);
                        toBeAdded.put(mainBaseKey, orAll);
                        toBeRemoved.addAll(keysByBaseKey);
                    } else if (!hasNumber(key)) {// g_p_r_s
                        criterionList.add(collectionsCriterion.get(key));
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
            criterionList.add(next);
        }
        if (criterionList.isEmpty()) {
            return null;
        }
        return andAll(criteriaBuilder, criterionList);
    }

    default void addCriterionForProperty(
            CriteriaBuilder criteriaBuilder,
            IBaseCriteriaFromProvider<Projection> fromProvider,
            Map<String, Criterion> criterionListMap,
            String parentPTN,
            String propertyName,
            HashMap<String, Object> map,
            SearchJoinType joinType) throws InterceptionInterruptException {
        Object propertyValue = map.get(propertyName);
        Criterion propertyCriterion = getPropertyCriterion(fromProvider.getCriteriaParentProjection(getPropertyTreeName(parentPTN, propertyName), joinType), propertyValue, criteriaBuilder);
        String childPropTreeName = getPropertyTreeName(parentPTN, propertyName);
        // notice: childPropTreeName may have numbers in it for collections
        criterionListMap.put(childPropTreeName, propertyCriterion);
    }

    default String getPropertyTreeName(String parentTreeName, String propertyName) {
        return parentTreeName.equals("") ? propertyName : (parentTreeName + "." + propertyName);
    }

    default String getChildFromPTN(String propertyTreeName) {
        int dotIndex = propertyTreeName.lastIndexOf('.');
        return propertyTreeName.substring(dotIndex + 1);
    }

    default String getParentTreeNameFromPTN(String propertyTreeName) {
        int dotIndex = propertyTreeName.lastIndexOf('.');
        return propertyTreeName.substring(0, dotIndex);
    }

    default int lastNumberIndex(String parentKey) {
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

    default boolean hasNumber(String key) {
        // commented because of performance problems
        // return Pattern.compile("(.*)[0-9](.*)").matcher(key).matches();
        return key.contains("1") || key.contains("2") || key.contains("3") || key.contains("4") || key.contains("5") || key.contains("6") || key.contains("7")
                || key.contains("8") || key.contains("9") || key.contains("0");
    }

    /**
     * put an object to a Map
     *
     * @param example
     * @return
     */
    default HashMap<String, Object> getMapFromObjectNonRecursive(Object example) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(example.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                List<Class<?>> classes = propertyClassesToBeIntercepted();
                classes.forEach(c -> checkPropertyForClass(example, result, propertyDescriptor, c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    default List<Class<?>> propertyClassesToBeIntercepted() {
        ArrayList<Class<?>> classes = new ArrayList<>();
        classes.add(Number.class);
        classes.add(Date.class);
        classes.add(String.class);
        classes.add(Boolean.class);
        classes.add(Enum.class);
        return classes;
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
    default void checkPropertyForClass(Object example, HashMap<String, Object> result, PropertyDescriptor propertyDescriptor, Class<?> query) {
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

}
