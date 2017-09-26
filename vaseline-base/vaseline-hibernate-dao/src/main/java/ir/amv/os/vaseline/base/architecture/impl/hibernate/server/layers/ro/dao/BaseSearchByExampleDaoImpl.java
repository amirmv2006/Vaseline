package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.dao.BaseDaoImpl;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by AMV on 9/26/2017.
 */
public class BaseSearchByExampleDaoImpl<D>
            extends BaseDaoImpl {
    protected String getChildFromPTN(String propertyTreeName) {
        int dotIndex = propertyTreeName.lastIndexOf('.');
        String childName = propertyTreeName.substring(dotIndex + 1);
        return childName;
    }

    protected String getParentTreeNameFromPTN(String propertyTreeName) {
        int dotIndex = propertyTreeName.lastIndexOf('.');
        String parentName = propertyTreeName.substring(0, dotIndex);
        return parentName;
    }

    protected String getParentFromPTN(String propertyTreeName) {
        return getParentTreeNameFromPTN(propertyTreeName).replaceAll("\\.", "_");
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

    protected boolean hasNumber(String key) {
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
}
