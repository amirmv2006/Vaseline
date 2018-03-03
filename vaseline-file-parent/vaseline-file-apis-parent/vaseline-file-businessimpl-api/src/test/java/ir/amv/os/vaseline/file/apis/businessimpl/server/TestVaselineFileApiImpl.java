package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.executor.IVaselineBusinessActionExecutor;
import ir.amv.os.vaseline.file.apis.business.server.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.apis.dao.basic.server.IVaselineFileDao;
import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.apis.dao.jpa.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class TestVaselineFileApiImpl
        implements IImplementedVaselineFileApi {
    private List<IVaselineFileDao> fileDaoList;
    private Object proxy;
    private IVaselineBusinessActionExecutor businessActionExecutor;

    @Override
    public List<IVaselineFileDaoFinder> getDaoFinderList() {
        return Arrays.asList(new IVaselineFileDaoFinder() {
            @Override
            public Integer priority() {
                return 1;
            }

            @Override
            public IVaselineFileDao getDaoFor(String category, List<IVaselineFileDao> fileDaos) {
                for (IVaselineFileDao fileDao : fileDaos) {
                    if (category.equals("blob") && fileDao instanceof IVaselineFileBlobDao) {
                        IVaselineFileBlobDao blobDao = (IVaselineFileBlobDao) fileDao;
                        return blobDao;
                    } else if (category.equals("path") && fileDao instanceof IVaselineFilePathDao) {
                        IVaselineFilePathDao pathDao = (IVaselineFilePathDao) fileDao;
                        return pathDao;
                    }
                }
                return null;
            }
        });
    }

    @Override
    public List<IVaselineFileDao> getFileDaoList() {
        return fileDaoList;
    }

    @Override
    public IAuthenticationApi getAuthenticationApi() {
        return new IAuthenticationApi() {
            private Object proxy;

            @Override
            public <R> R doBusinessAction(final IBusinessAction<R> businessAction) throws BaseVaselineServerException {
                return null;
            }

            @Override
            public <Proxy> Proxy getProxy(Class<Proxy> proxyClass) {
                return (Proxy) proxy;
            }

            @Override
            public <Proxy> void setProxy(Proxy proxy) {
                this.proxy = proxy;
            }

            @Override
            public String getCurrentUsername() {
                return "testUser";
            }
        };
    }

    @Override
    public <Proxy> Proxy getProxy(Class<Proxy> proxyClass) {
        return (Proxy) proxy;
    }

    @Override
    public <Proxy> void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    @Inject
    public void setFileDaoList(List<IVaselineFileDao> fileDaoList) {
        this.fileDaoList = fileDaoList;
    }

    @Override
    public IVaselineBusinessActionExecutor getBusinessActionExecutor() {
        return businessActionExecutor;
    }

    @Inject
    public void setBusinessActionExecutor(final IVaselineBusinessActionExecutor businessActionExecutor) {
        this.businessActionExecutor = businessActionExecutor;
    }
}
