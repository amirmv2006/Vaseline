package ir.amv.os.vaseline.basics.logging.api.server.exc;

/**
 * @author Amir
 */
public class LogInterruptOthersException
        extends LogException {

    private String logMessage;

    public LogInterruptOthersException(final String logMessage) {
        this.logMessage = logMessage;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
