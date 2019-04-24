package ir.amv.os.vaseline.file.layers.spring.basic.server.api;

import ir.amv.os.vaseline.basics.core.api.server.proxy.defimpl.ProxyAwareImpl;
import ir.amv.os.vaseline.file.business.api.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.business.def.IDefaultVaselineFileApi;
import ir.amv.os.vaseline.file.dao.basic.api.IVaselineFileDao;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Amir
 */
public class VaselineFileApiImpl
        extends ProxyAwareImpl
        implements IDefaultVaselineFileApi {

    private IAuthenticationApi authenticationApi;
    private List<IVaselineFileDao> fileDaoList;
    private List<IVaselineFileDaoFinder> daoFinderList;

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
