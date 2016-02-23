package ir.amv.os.vaseline.base.architecture.server.layers.parent.api;

public interface IBaseApi {

    <API> API getApiProxy();
    <API> void setApiProxy(API apiProxy);

}
