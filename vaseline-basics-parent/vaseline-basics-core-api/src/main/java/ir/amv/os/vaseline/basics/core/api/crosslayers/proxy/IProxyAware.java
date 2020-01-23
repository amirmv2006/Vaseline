package ir.amv.os.vaseline.basics.core.api.crosslayers.proxy;

/**
 * Created by AMV on 2/24/2016.
 */
public interface IProxyAware {

    <P> P getProxy(Class<P> proxyClass);
    <P> void setProxy(P proxy);
}
