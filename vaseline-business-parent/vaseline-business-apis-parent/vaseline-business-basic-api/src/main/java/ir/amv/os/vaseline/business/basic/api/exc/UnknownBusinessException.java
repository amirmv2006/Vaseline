package ir.amv.os.vaseline.business.basic.api.exc;

/**
 * Thrown because of a {@link RuntimeException} exception.
 */
public class UnknownBusinessException extends BaseBusinessException {

    /**
     * The only constructor! This exception should not be thrown under any other circumstances.
     * @param cause   the cause {@link RuntimeException}
     */
    public UnknownBusinessException(RuntimeException cause) {
        super("Unknown Exception: " + cause.getMessage(), cause);
    }
}
