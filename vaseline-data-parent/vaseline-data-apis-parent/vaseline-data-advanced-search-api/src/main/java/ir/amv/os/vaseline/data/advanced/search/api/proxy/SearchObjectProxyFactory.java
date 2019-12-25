package ir.amv.os.vaseline.data.advanced.search.api.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amv on 12/8/16.
 */
public class SearchObjectProxyFactory {

    public static final String IMPL_PACKAGE = "impl";
    public static final String IMPL_CLASS_TAIL = "Impl";

    public static <S> S proxy(Class<S> searchObjectClass) {
        String packageName = searchObjectClass.getPackage().getName();
        String implPackageName = packageName.equals("") ? IMPL_PACKAGE : packageName + "." + IMPL_PACKAGE;
        String simpleName = searchObjectClass.getSimpleName();
        if (simpleName.startsWith("I")) {
            simpleName = simpleName.substring(1);
        }
        String implClassName = implPackageName + "." + simpleName + IMPL_CLASS_TAIL;
        Object proxy;
        try {
            Class<?> aClass = Class.forName(implClassName);
            proxy = aClass.newInstance();
        } catch (Exception e) {
            proxy = Proxy.newProxyInstance(searchObjectClass.getClassLoader(), new Class[]{searchObjectClass}, new InvocationHandler() {
                Map<String, Object> dataHolder = new HashMap<String, Object>();
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if (method.getName().startsWith("set")) {
                        dataHolder.put(method.getName().substring("set".length()), args[0]);
                        return null;
                    } else if (method.getName().startsWith("get")) {
                        return dataHolder.get(method.getName().substring("get".length()));
                    }
                    return null;
                }
            });
        }
        return (S) proxy;
    }
}
