package ir.amv.os.vaseline.basics.apis.core.server.proxyaware.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;

/**
 * @author Amir
 */
public class ProxyAwareImpl
        implements IProxyAware {
    private Object proxy;

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {
        this.proxy = proxy;
    }
}
