package ir.amv.os.vaseline.base.core.server.proxyaware;

/**
 * Created by AMV on 2/24/2016.
 */
public interface IProxyAware {

    <Proxy> Proxy getProxy(Class<Proxy> proxyClass);
    <Proxy> void setProxy(Proxy proxy);
}
