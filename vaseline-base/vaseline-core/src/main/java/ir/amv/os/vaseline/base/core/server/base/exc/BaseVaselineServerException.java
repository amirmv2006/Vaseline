package ir.amv.os.vaseline.base.core.server.base.exc;

/**
 * Created by AMV on 2/3/2016.
 */
public class BaseVaselineServerException extends Exception {

    public BaseVaselineServerException() {
    }

    public BaseVaselineServerException(String message) {
        super(message);
    }

    public BaseVaselineServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseVaselineServerException(Throwable cause) {
        super(cause);
    }
}
