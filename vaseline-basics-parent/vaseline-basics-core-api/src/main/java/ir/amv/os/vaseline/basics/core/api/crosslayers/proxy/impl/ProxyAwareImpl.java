package ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.impl;

import ir.amv.os.vaseline.basics.core.api.crosslayers.proxy.IProxyAware;

/**
 * @author Amir
 */
public class ProxyAwareImpl
        implements IProxyAware {
    private Object proxy;

    @Override
    public <P> P getProxy(final Class<P> proxyClass) {
        return (P) proxy;
    }

    @Override
    public <P> void setProxy(final P proxy) {
        this.proxy = proxy;
    }
}
