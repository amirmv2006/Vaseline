package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ConverterDetector.class)
public @interface EnableAutoConverterDetector {

    String basePackage();
}
