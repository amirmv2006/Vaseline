package ir.amv.os.vaseline.base.architecture.impl.server.layers.parent.api;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.api.IBaseApi;

public class BaseApiImpl implements IBaseApi {

    private Object apiProxy;

    @Override
    public <API> API getApiProxy() {
        return (API) apiProxy;
    }

    @Override
    public void setApiProxy(Object apiProxy) {
        this.apiProxy = apiProxy;
    }
}
