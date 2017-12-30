package ir.amv.os.vaseline.basics.osgi.base.deferred;

/**
 * @author Amir
 */
public interface IDeferredTask<S> {

    void executeNow(S service);
    void executeLater(S service);

}
