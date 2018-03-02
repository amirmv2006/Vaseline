package ir.amv.os.vaseline.basics.apis.core.shared.util.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AMV on 10/3/2017.
 */
public class MapOfList<K, V> extends HashMap<K, List<V>> {
    public void add(K key, V value) {
        List<V> list = get(key);
        if (list == null) {
            list = new ArrayList<V>();
            put(key, list);
        }
        list.add(value);
    }
}
