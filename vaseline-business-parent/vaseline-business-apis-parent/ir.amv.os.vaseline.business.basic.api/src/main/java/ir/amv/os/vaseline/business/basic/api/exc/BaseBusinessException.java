package ir.amv.os.vaseline.business.basic.api.exc;

/**
 * Created by AMV on 2/3/2016.
 */
public abstract class BaseBusinessException extends Exception {

    public BaseBusinessException() {
    }

    public BaseBusinessException(String message) {
        super(message);
    }

    public BaseBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseBusinessException(Throwable cause) {
        super(cause);
    }
}
