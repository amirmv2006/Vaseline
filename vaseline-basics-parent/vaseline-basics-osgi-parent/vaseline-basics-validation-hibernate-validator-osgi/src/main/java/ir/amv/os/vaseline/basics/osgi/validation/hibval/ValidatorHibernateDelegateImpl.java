package ir.amv.os.vaseline.basics.osgi.validation.hibval;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.osgi.service.component.annotations.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = Validator.class
)
public class ValidatorHibernateDelegateImpl implements Validator {

    private Validator getValidator() {
        return Validation.byProvider( HibernateValidator.class )
                .configure()
                .messageInterpolator( new ResourceBundleMessageInterpolator(
                        new PlatformResourceBundleLocator( ResourceBundleMessageInterpolator.USER_VALIDATION_MESSAGES ),
                        true)
                ).externalClassLoader( getClass().getClassLoader() )
                .buildValidatorFactory()
                .getValidator();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(final T t, final Class<?>... classes) {
        return getValidator().validate(t, classes);
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(final T t, final String s, final Class<?>... classes) {
        return getValidator().validateProperty(t, s, classes);
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(final Class<T> aClass, final String s, final Object o, final Class<?>... classes) {
        return getValidator().validateValue(aClass, s, o, classes);
    }

    @Override
    public BeanDescriptor getConstraintsForClass(final Class<?> aClass) {
        return getValidator().getConstraintsForClass(aClass);
    }

    @Override
    public <T> T unwrap(final Class<T> aClass) {
        return getValidator().unwrap(aClass);
    }

    @Override
    public ExecutableValidator forExecutables() {
        return getValidator().forExecutables();
    }
}
