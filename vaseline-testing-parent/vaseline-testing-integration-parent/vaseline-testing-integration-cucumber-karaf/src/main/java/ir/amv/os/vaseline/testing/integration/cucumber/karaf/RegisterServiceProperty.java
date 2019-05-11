package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RegisterServiceProperty {
    String propertyName();
    String propertyType() default RegisterServicePropertyTypeConstants.STRING_PROPERTY_TYPE; // using enum throws exception on remote call
    String strValue() default "";
    boolean boolValue() default false;
    int intValue() default -1;
}
