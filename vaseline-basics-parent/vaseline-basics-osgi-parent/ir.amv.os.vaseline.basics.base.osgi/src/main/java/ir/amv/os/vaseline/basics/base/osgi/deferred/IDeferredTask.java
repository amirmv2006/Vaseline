package ir.amv.os.vaseline.basics.base.osgi.deferred;

/**
 * @author Amir
 */
public interface IDeferredTask<S> {

    void executeNow(S service);
    void executeLater(S service);

}
