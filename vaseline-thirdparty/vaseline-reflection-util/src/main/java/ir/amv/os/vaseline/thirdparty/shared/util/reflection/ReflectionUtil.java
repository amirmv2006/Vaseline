package ir.amv.os.vaseline.thirdparty.shared.util.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class ReflectionUtil {

    public static <E> void setNonDtoPropsToNull(E ent, final Class<?> dtoClass) throws IntrospectionException {
        if (ent == null) {
            return;
        }
        final Class<? extends E> objClass = (Class<? extends E>) ent.getClass();
        intercept(ent, objClass, new ReflectionInterceptor<E>() {
            @Override
            public E intercept(E object, String propertyTreeName) {
                if (getPropertyTypeByTreeName(dtoClass, propertyTreeName, objClass, dtoClass) != null) {
                    return object;
                }
                return null;
            }
        }, "");
    }

    public static Class<?> getPropertyTypeByTreeName(Class<?> srcClass, String propertyTreeName, Class<?>... parentClasses) {
        try {
            if (propertyTreeName.equals("")) {
                return srcClass;
            }
            if (parentClasses != null) {
                for (Class<?> parentClass : parentClasses) {
                    if (propertyTreeName.equals("id") && parentClass.isAssignableFrom(srcClass)) {
                        return getGenericArgumentClasses(srcClass, parentClass)[0];
                    }
                }
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(srcClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                if (propertyDescriptors[i].getReadMethod() == null) {
                    continue;
                }
                Class<?> propertyClass = propertyDescriptors[i].getReadMethod().getReturnType();
                if (propertyTreeName.contains(".")) {
                    String[] split = propertyTreeName.split("\\.");
                    String parentName = split[0];
                    String childPropTreeName = "";
                    for (int j = 1; j < split.length; j++) {
                        childPropTreeName += split[j];
                        if (j < split.length - 1) {
                            childPropTreeName += ".";
                        }
                    }
                    if (parentName.startsWith(propertyDescriptors[i].getName())) {
                        Type genericReturnType = propertyDescriptors[i]
                                .getReadMethod().getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            ParameterizedType type = (ParameterizedType) genericReturnType;
                            Type[] typeArguments = type
                                    .getActualTypeArguments();
                            for (Type typeArgument : typeArguments) {
                                if (typeArgument instanceof Class<?>) {
                                    propertyClass = (Class<?>) typeArgument;
                                }
                            }
                        }
                        return getPropertyTypeByTreeName(propertyClass, childPropTreeName, parentClasses);
                    }
                } else if (propertyTreeName.equals(propertyDescriptors[i].getName())) {
                    return propertyClass;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PropertyDescriptor getPropertyDescriptorByTreeName(Class<?> srcClass, String propertyTreeName) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(srcClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                if (propertyDescriptors[i].getReadMethod() == null) {
                    continue;
                }
                Class<?> propertyClass = propertyDescriptors[i].getReadMethod().getReturnType();
                if (propertyTreeName.contains(".")) {
                    String[] split = propertyTreeName.split("\\.");
                    String parentName = split[0];
                    String childPropTreeName = "";
                    for (int j = 1; j < split.length; j++) {
                        childPropTreeName += split[j];
                        if (j < split.length - 1) {
                            childPropTreeName += ".";
                        }
                    }
                    if (parentName.startsWith(propertyDescriptors[i].getName())) {
                        Type genericReturnType = propertyDescriptors[i]
                                .getReadMethod().getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            ParameterizedType type = (ParameterizedType) genericReturnType;
                            Type[] typeArguments = type
                                    .getActualTypeArguments();
                            for (Type typeArgument : typeArguments) {
                                if (typeArgument instanceof Class<?>) {
                                    propertyClass = (Class<?>) typeArgument;
                                }
                            }
                        }
                        return getPropertyDescriptorByTreeName(propertyClass, childPropTreeName);
                    }
                } else if (propertyTreeName.equals(propertyDescriptors[i].getName())) {
                    return propertyDescriptors[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param src              can be null!
     * @param propertyTreeName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getPropertyByTreeName(Object src, String propertyTreeName) {
        if (src == null) {
            return null;
        }
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(src.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            String[] split = propertyTreeName.split("\\.");
            String parentName = split[0];
            String childPropTreeName = "";
            for (int j = 1; j < split.length; j++) {
                childPropTreeName += split[j];
                if (j < split.length - 1) {
                    childPropTreeName += ".";
                }
            }
            for (int i = 0; i < propertyDescriptors.length; i++) {
                if (propertyDescriptors[i].getReadMethod() == null) {
                    continue;
                }
                Object propertyValue = propertyDescriptors[i].getReadMethod().invoke(src);
                if (propertyTreeName.contains(".")) {
                    if (parentName.equals(propertyDescriptors[i].getName())) {
                        if (propertyValue instanceof Collection<?>) {
                            Collection<?> coll = (Collection<?>) propertyValue;
                            Object next = null;
                            try {
                                next = coll.iterator().next();
                                return getPropertyByTreeName(next, childPropTreeName);
                            } catch (Exception e) {
                                return null;
                            }
                        } else {
                            return getPropertyByTreeName(propertyValue, childPropTreeName);
                        }
                    }
                } else if (propertyTreeName.equals(propertyDescriptors[i].getName())) {
                    if (propertyValue instanceof Collection<?>) {
                        Collection<?> coll = (Collection<?>) propertyValue;
                        try {
                            return (T) coll.iterator().next();
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    return (T) propertyValue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> searchMethodReturnTypeForEnum(Method method) {
        Class<?> returnType = method.getReturnType();
        if (returnType.isEnum()) {
            return returnType;
        } else {
            return null;
        }
    }

    public static Class<?> searchMethodReturnType(Method method, Class<?> query) {
        Class<?> returnType = method.getReturnType();
        if (query.isAssignableFrom(returnType)) {
            return returnType;
        } else {
            Type genericReturnType = method.getGenericReturnType();
            if (genericReturnType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) genericReturnType;
                Type[] typeArguments = type.getActualTypeArguments();
                for (Type typeArgument : typeArguments) {
                    if (typeArgument instanceof Class<?>
                            && query.isAssignableFrom((Class<?>) typeArgument)) {
                        Class<?> c = (Class<?>) typeArgument;
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public static Object[] getMethodResultAsArray(Object obj, Method method,
                                                  Class<?> query) {
        try {
            Class<?> returnType = method.getReturnType();
            if (query.isAssignableFrom(returnType)) {
                Object result = method.invoke(obj);
                return new Object[]{result};
            } else if (Collection.class.isAssignableFrom(returnType)) {
                Collection<?> coll = (Collection<?>) method.invoke(obj);
                return coll == null ? null : coll.toArray();
            } else if (Map.class.isAssignableFrom(returnType)) {
                Map<?, ?> coll = (Map<?, ?>) method.invoke(obj);
                return coll.values().toArray();
            }
        } catch (Exception e) {
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setObjectsToMethod(Object obj, Method readerMethod, Method writerMethod, Object[] values, Object[] oldValue) {
        try {
            Class<?> parameterType = writerMethod.getParameterTypes()[0];
            if (List.class.isAssignableFrom(parameterType)) {
                List list = (List) readerMethod.invoke(obj);
                list = (list == null ? new ArrayList() : list);
                for (int i = 0; i < oldValue.length; i++) {
                    int index = list.indexOf(oldValue[i]);
                    if (values[i] == null) {
                        list.remove(index);
                    } else {
                        list.set(index, values[i]);
                    }
                }
            } else if (Set.class.isAssignableFrom(parameterType)) {
                Set set = (Set) readerMethod.invoke(obj);
                set = (set == null ? new HashSet() : set);
                set.removeAll(Arrays.asList(oldValue));
                for (int i = 0; i < values.length; i++) {
                    if (values[i] != null) {
                        set.add(values[i]);
                    }
                }
            } else if (Map.class.isAssignableFrom(parameterType)) {
                Map map = (Map) readerMethod.invoke(obj);
                for (int i = 0; i < oldValue.length; i++) {
                    ArrayList<Object> toBeRemoved = new ArrayList<Object>();
                    for (Object key : map.keySet()) {
                        if (map.get(key) == oldValue[i]) {
                            if (values[i] == null) {
                                toBeRemoved.add(key);
                            } else {
                                map.put(key, values[i]);
                            }
                            break;
                        }
                    }
                    for (Object object : toBeRemoved) {
                        map.remove(object);
                    }
                }
            } else {
                writerMethod.invoke(obj, values[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <Q, QP extends Q, O> O intercept(O src, Class<QP> query,
                                                   ReflectionInterceptor<Q> interceptor, String prefix) {
        if (src == null) {
            return null;
        }
        Class<? extends Object> objClass = src.getClass();
        try {
            O obj = src;
            // check If the object itself should be intercepted!
            if (query.isAssignableFrom(objClass)) {
                Q intercepted = interceptor.intercept((Q) src, prefix.trim().equals("") ? "" : prefix);
                obj = (O) intercepted;
            }

            BeanInfo beanInfo = Introspector.getBeanInfo(objClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo
                    .getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
                Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod == null) {
                    continue;
                }
                if (readMethod.isBridge()) {
                    readMethod = objClass.getMethod(readMethod.getName(), readMethod.getParameterTypes());
                }
                if (searchMethodReturnType(readMethod, query) != null) {
                    Object[] objectsFromMethod = getMethodResultAsArray(obj,
                            readMethod, query);
                    String propertyTreeName = (prefix == null || prefix.equals("")) ? propertyDescriptor.getName() : prefix + "." + propertyDescriptor.getName();
                    if (objectsFromMethod != null) {
                        for (int j = 0; j < objectsFromMethod.length; j++) { // go for children recursively...
                            intercept(objectsFromMethod[j], query, interceptor, objectsFromMethod.length > 1 ? propertyTreeName + j : propertyTreeName);
                        }
                        // intercept the properties now...
                        Object[] newObjects = new Object[objectsFromMethod.length];
                        for (int j = 0; j < objectsFromMethod.length; j++) {
                            Q intercepted = interceptor
                                    .intercept((Q) objectsFromMethod[j], objectsFromMethod.length > 1 ? propertyTreeName + j : propertyTreeName);
                            newObjects[j] = intercepted;
                        }
                        setObjectsToMethod(obj, readMethod, propertyDescriptor.getWriteMethod(), newObjects, objectsFromMethod);
                    }
                }
            }
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return src;
    }

    public static <A extends Annotation> A getAnnotationFromFieldOrGetter(Class<?> targetClass, String fieldName, Class<A> annotationClass) {
        Field declaredField;
        Class<?> classCnt = targetClass;
        while (classCnt != null) {
            try {
                declaredField = classCnt.getDeclaredField(fieldName);
                A annotation = declaredField.getAnnotation(annotationClass);
                if (annotation != null) {
                    return annotation;
                }
            } catch (NoSuchFieldException e) {
            }
            classCnt = classCnt.getSuperclass();
        }
        PropertyDescriptor propertyDescriptorByTreeName = getPropertyDescriptorByTreeName(targetClass, fieldName);
        A annotation = propertyDescriptorByTreeName.getReadMethod().getAnnotation(annotationClass);
        return annotation;
    }

    public static <A extends Annotation> A getAnnotationInHierarchy(Class<?> targetClass, Class<A> annotationClass) {
        if (targetClass == null || annotationClass == null) {
            return null;
        }
        if (targetClass.isAnnotationPresent(annotationClass)) {
            return targetClass.getAnnotation(annotationClass);
        }
        Class<?> superclass = targetClass.getSuperclass();
        A hasAnnotation = getAnnotationInHierarchy(superclass, annotationClass);
        if (hasAnnotation != null) {
            return hasAnnotation;
        }
        Class<?>[] interfaces = targetClass.getInterfaces();
        if (interfaces != null) {
            for (Class<?> intfClass : interfaces) {
                A intAnnot = getAnnotationInHierarchy(intfClass, annotationClass);
                if (intAnnot != null) {
                    return intAnnot;
                }
            }
        }
        return null;
    }

    public static Class<?>[] getGenericArgumentClasses(Class<?> genericClass, Class<?>... parents) {
        if (genericClass.getGenericSuperclass() instanceof ParameterizedType) {
            Class<?>[] result = getGenerics(genericClass.getGenericSuperclass());
            return result;
        } else {
            Type[] genericInterfaces = genericClass.getGenericInterfaces();
            if (genericInterfaces.length == 0) {
                return null;
            }
            if (parents != null) {
                for (Class<?> parent : parents) {
                    for (Type anInterface : genericInterfaces) {
                        Class rawType = Void.class;
                        if (anInterface instanceof ParameterizedType) {
                            ParameterizedType parameterizedType = (ParameterizedType) anInterface;
                            rawType = (Class) parameterizedType.getRawType();
                        } else if (anInterface instanceof Class) {
                            rawType = (Class) anInterface;
                        }
                        if (parent.isAssignableFrom(rawType)) {
                            return getGenerics(anInterface);
                        }
                    }
                }
            }
            return getGenerics(genericInterfaces[0]);
        }
    }

    private static Class<?>[] getGenerics(Type genericClass) {
        ParameterizedType genericSuperclass = (ParameterizedType) genericClass;
        if (genericSuperclass != null && genericSuperclass.getActualTypeArguments() != null) {
            Class<?>[] result = new Class<?>[genericSuperclass.getActualTypeArguments().length];
            for (int i = 0; i < result.length; i++) {
                if (genericSuperclass.getActualTypeArguments()[i] instanceof Class) {
                    result[i] = (Class<?>) genericSuperclass.getActualTypeArguments()[i];
                } else if (genericSuperclass.getActualTypeArguments()[i] instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) genericSuperclass.getActualTypeArguments()[i]).getRawType();
                    if (rawType instanceof Class<?>) {
                        result[i] = (Class<?>) rawType;
                    }
                }
            }
            return result;
        }
        return null;
    }

    public static List<PropertyDescriptor> getPropertyDescriptors(
            Class<?> beanClass) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        List<PropertyDescriptor> result = new ArrayList<PropertyDescriptor>();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor propertyDescriptor = propertyDescriptors[i];
            if (propertyDescriptor.getReadMethod() == null || propertyDescriptor.getWriteMethod() == null) {
                continue;
            }
            result.add(propertyDescriptors[i]);
        }
        return result;
    }

    public static boolean isPrimitive(Class<?> clazz) {
        if (clazz.isPrimitive()) {
            return true;
        }
        if (String.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Number.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Enum.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Date.class.isAssignableFrom(clazz)) {
            return true;
        }
        if (Boolean.class.isAssignableFrom(clazz)) {
            return true;
        }
        return false;
    }

}
