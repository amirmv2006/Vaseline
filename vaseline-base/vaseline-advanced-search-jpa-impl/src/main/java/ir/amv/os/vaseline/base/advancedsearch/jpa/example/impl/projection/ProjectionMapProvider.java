package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection;

import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import java.util.HashMap;

/**
 * Created by amv on 12/12/16.
 */
public class ProjectionMapProvider<E>
        extends HashMap<String, Path<?>> {

    CriteriaQuery<E> query;
    Class<E> entityClass;

    public ProjectionMapProvider(CriteriaQuery<E> query, Class<E> entityClass) {
        this.query = query;
        this.entityClass = entityClass;
    }

    @Override
    public Path<?> get(Object key) {
        if (key instanceof String) {
            String propTN = (String) key;
            Path path = super.get(propTN);
            if (path == null) {
                if (propTN.equals("")) {
                    Class<?> propertyTypeByTreeName = ReflectionUtil.getPropertyTypeByTreeName(entityClass, propTN);
                    path = query.from(propertyTypeByTreeName);
                } else {
                    String parent;
                    String child;
                    if (!propTN.contains(".")) {
                        int lastDot = propTN.lastIndexOf(".");
                        parent = propTN.substring(0, lastDot);
                        child = propTN.substring(lastDot + 1);
                    } else {
                        parent = "";
                        child = propTN;
                    }
                    path = get(parent).get(child);
                }

                put(propTN, path);
            }
            return path;
        }
        return super.get(key);
    }
}
