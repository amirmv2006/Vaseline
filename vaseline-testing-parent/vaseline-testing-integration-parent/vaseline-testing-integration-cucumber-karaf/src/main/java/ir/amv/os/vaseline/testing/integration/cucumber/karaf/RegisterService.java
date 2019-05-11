package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterService {
    Class<?> implClass();
    Class<?> interfaceClass();
    RegisterServiceProperty[] properties() default {};
}
