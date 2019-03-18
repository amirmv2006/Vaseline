package ir.amv.os.vaseline.basics.mapper.api.server.exc;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;

/**
 * Created by AMV on 1/16/2016.
 */
public class VaselineConvertException extends BaseVaselineServerException {

    public VaselineConvertException() {
    }

    public VaselineConvertException(String message) {
        super(message);
    }

    public VaselineConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public VaselineConvertException(Throwable cause) {
        super(cause);
    }
}
