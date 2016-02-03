package ir.amv.os.vaseline.base.core.shared.util.reflection;

public interface ReflectionInterceptor<Q> {

	Q intercept(Q object, String propertyTreeName);

}
