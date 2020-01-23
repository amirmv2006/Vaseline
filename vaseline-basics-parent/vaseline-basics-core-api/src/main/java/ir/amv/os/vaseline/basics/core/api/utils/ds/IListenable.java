package ir.amv.os.vaseline.basics.core.api.utils.ds;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Amir
 */
public interface IListenable<L> {
    List<L> getListeners();

    default void notify(Consumer<L> listenerConsumer) {
        getListeners().forEach(listenerConsumer);
    }

    default void addListener(L listener) {
        List<L> listeners = getListeners();
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    default void removeListener(L listener) {
        getListeners().remove(listener);
    }
}
