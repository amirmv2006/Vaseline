package ir.amv.os.vaseline.base.mapper.config.hibernate;

import org.hibernate.collection.internal.PersistentList;
import org.hibernate.collection.internal.PersistentSet;
import org.hibernate.proxy.HibernateProxy;

//FIXME remove this
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
			try {
				hp.getHibernateLazyInitializer().getImplementation();
			} catch (Exception e) {
				return false;
			}
		}
		return true;

	}
}
