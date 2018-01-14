package ir.amv.os.vaseline.ws.rest.server.multiparam.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by AMV on 2/14/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {
    String paramName();
    Class<?> paramType();
}
