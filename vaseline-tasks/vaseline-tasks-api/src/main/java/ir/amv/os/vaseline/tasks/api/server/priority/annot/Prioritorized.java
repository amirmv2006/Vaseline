package ir.amv.os.vaseline.tasks.api.server.priority.annot;

import ir.amv.os.vaseline.tasks.api.server.priority.prioritorizer.IBaseVaselineAsyncPrioritorizer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by AMV on 2/29/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Prioritorized {
    int value() default 0;
    Class<? extends IBaseVaselineAsyncPrioritorizer> prioritorizerClass() default IBaseVaselineAsyncPrioritorizer.class;
}
