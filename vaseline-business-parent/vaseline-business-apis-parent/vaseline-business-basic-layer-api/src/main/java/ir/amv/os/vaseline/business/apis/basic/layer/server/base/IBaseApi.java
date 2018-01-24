package ir.amv.os.vaseline.business.apis.basic.layer.server.base;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;

public interface IBaseApi extends IProxyAware {

    <R> R doBusinessAction(IBusinessAction<R> businessAction) throws BaseVaselineServerException;

}
