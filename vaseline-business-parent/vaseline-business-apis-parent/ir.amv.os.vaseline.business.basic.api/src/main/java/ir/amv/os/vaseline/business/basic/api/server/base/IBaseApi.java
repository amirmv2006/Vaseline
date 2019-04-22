package ir.amv.os.vaseline.business.basic.api.server.base;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.server.proxyaware.IProxyAware;
import ir.amv.os.vaseline.business.basic.api.server.action.IBusinessAction;

public interface IBaseApi extends IProxyAware {

    <R> R doBusinessAction(IBusinessAction<R> businessAction) throws BaseVaselineServerException;

}
