package ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;

public class BaseApiImpl implements IBaseApi {

    private Object proxy;

    @Override
    public <Proxy> Proxy getProxy(Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

}
