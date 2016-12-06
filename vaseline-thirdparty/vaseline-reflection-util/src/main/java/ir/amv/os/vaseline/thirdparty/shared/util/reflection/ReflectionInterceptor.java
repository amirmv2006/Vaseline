package ir.amv.os.vaseline.thirdparty.shared.util.reflection;

public interface ReflectionInterceptor<Q> {

	Q intercept(Q object, String propertyTreeName);

}
