package ir.amv.os.vaseline.file.layers.spring.basic.server.api;

import ir.amv.os.vaseline.file.apis.business.server.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.apis.businessimpl.server.IImplementedVaselineFileApi;
import ir.amv.os.vaseline.file.apis.dao.server.IVaselineFileDao;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Amir
 */
public class VaselineFileApiImpl
        implements IImplementedVaselineFileApi {

    private IAuthenticationApi authenticationApi;
    private List<IVaselineFileDao> fileDaoList;
    private List<IVaselineFileDaoFinder> daoFinderList;
    private Object proxy;

    @Override
    public List<IVaselineFileDaoFinder> getDaoFinderList() {
        return daoFinderList;
    }

    @Override
    public List<IVaselineFileDao> getFileDaoList() {
        return fileDaoList;
    }

    @Override
    public IAuthenticationApi getAuthenticationApi() {
        return authenticationApi;
    }

    @Override
    public <Proxy> Proxy getProxy(final Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(final Proxy proxy) {
        this.proxy = proxy;
    }

    @Autowired
    public void setDaoFinderList(final List<IVaselineFileDaoFinder> daoFinderList) {
        this.daoFinderList = daoFinderList;
    }

    @Autowired
    public void setFileDaoList(final List<IVaselineFileDao> fileDaoList) {
        this.fileDaoList = fileDaoList;
    }

    @Autowired
    public void setAuthenticationApi(final IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }
}
