package ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.multiparam.annot;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by AMV on 2/14/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonMultParam {
    JsonParam[] value();
}
