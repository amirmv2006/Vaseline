package ir.amv.os.vaseline.base.mapper.server.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by AMV on 2/17/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.TYPE
})
public @interface VaselineMapTo {
    Class<?> mapToServerClass();
}
