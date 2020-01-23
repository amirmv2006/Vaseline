package ir.amv.os.vaseline.basics.spring.core.utils.reflection;

import org.springframework.core.GenericTypeResolver;

public final class GenericUtils {

    /**
     * utils class.
     */
    private GenericUtils() {
    }

    public static <O> Class<O> getGeneric(Class<?> childClass, Class<?> genericParentClass, int index) {
        return (Class<O>) getGenerics(childClass, genericParentClass)[index];
    }

    public static Class<?>[] getGenerics(Class<?> childClass, Class<?> genericParentClass) {
        return GenericTypeResolver.resolveTypeArguments(childClass, genericParentClass);
    }
}
