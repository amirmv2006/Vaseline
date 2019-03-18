package ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc;

/**
 * Created by AMV on 10/4/2017.
 */
public class InterceptionInterruptException
        extends InterceptionException {

    private Object interruptedValue;
    private Boolean keepOriginalValue;

    public InterceptionInterruptException() {
        this(true, null);
    }

    public InterceptionInterruptException(Object interruptedValue) {
        this(false, interruptedValue);
    }

    public InterceptionInterruptException(Boolean keepOriginalValue, Object interruptedValue) {
        this.interruptedValue = interruptedValue;
        this.keepOriginalValue = keepOriginalValue;
    }

    public Object getInterruptedValue() {
        return interruptedValue;
    }

    public Boolean getKeepOriginalValue() {
        return keepOriginalValue;
    }
}
