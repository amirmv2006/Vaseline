package ir.amv.os.vaseline.basics.logging.api.server.exc;

/**
 * @author Amir
 */
public class LogException
        extends RuntimeException {

    public LogException() {
    }

    public LogException(final String message) {
        super(message);
    }

    public LogException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
