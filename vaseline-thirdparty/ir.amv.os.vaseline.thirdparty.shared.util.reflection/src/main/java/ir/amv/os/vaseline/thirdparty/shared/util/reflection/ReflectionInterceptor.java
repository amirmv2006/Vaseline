package ir.amv.os.vaseline.thirdparty.shared.util.reflection;

import ir.amv.os.vaseline.thirdparty.shared.util.reflection.exc.InterceptionException;

public interface ReflectionInterceptor<Q> {

	Q intercept(Q object, String propertyTreeName) throws InterceptionException;

}
