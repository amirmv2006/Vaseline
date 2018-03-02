package ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Amir
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface VaselineBuinessMetadata {
    VaselineAllBuinessMetadata[] value();
}
