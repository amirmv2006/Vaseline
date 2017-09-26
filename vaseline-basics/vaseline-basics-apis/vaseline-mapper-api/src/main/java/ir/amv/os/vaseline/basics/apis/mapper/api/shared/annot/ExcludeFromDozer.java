package ir.amv.os.vaseline.basics.apis.mapper.api.shared.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by AMV on 2/17/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
        ElementType.FIELD,
        ElementType.TYPE
})
public @interface ExcludeFromDozer {
}
