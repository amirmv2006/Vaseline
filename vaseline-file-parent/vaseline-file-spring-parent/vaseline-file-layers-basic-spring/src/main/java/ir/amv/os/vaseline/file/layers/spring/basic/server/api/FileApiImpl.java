package ir.amv.os.vaseline.file.layers.spring.basic.server.api;

import ir.amv.os.vaseline.file.apis.business.server.daofinder.IFileDaoFinder;
import ir.amv.os.vaseline.file.apis.businessimpl.server.IImplementedFileApi;
import ir.amv.os.vaseline.file.apis.dao.server.IFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;
import ir.amv.os.vaseline.security.authentication.api.shared.api.IAuthenticationApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Amir
 */
public class FileApiImpl
        implements IImplementedFileApi{

    private IAuthenticationApi authenticationApi;
    private List<IFileDao> fileDaoList;
    private List<IFileDaoFinder> daoFinderList;
    private Object proxy;

    @Override
    public List<IFileDaoFinder> getDaoFinderList() {
        return daoFinderList;
    }

    @Override
    public List<IFileDao> getFileDaoList() {
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
    public void setDaoFinderList(final List<IFileDaoFinder> daoFinderList) {
        this.daoFinderList = daoFinderList;
    }

    @Autowired
    public void setFileDaoList(final List<IFileDao> fileDaoList) {
        this.fileDaoList = fileDaoList;
    }

    @Autowired
    public void setAuthenticationApi(final IAuthenticationApi authenticationApi) {
        this.authenticationApi = authenticationApi;
    }
}
