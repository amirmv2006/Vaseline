package ir.amv.os.vaseline.basics.core.api.utils.hibernate;

import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.proxy.HibernateProxy;

public class HibernateUtils {

    public static boolean isLazyObjectInitialized(Object value) {
        if (value instanceof PersistentSet) {
            PersistentSet ps = (PersistentSet) value;
            if (!ps.wasInitialized()) {
                return false;
            }
        } else if (value instanceof PersistentList) {
            PersistentList pl = (PersistentList) value;
            if (!pl.wasInitialized()) {
                return false;
            }
        } else if (value instanceof HibernateProxy) {
            HibernateProxy hp = (HibernateProxy) value;
            return !hp.getHibernateLazyInitializer().isUninitialized();
        }
        return true;

    }
}
