package ir.amv.os.vaseline.file.business.def;

import ir.amv.os.vaseline.basics.core.api.proxy.impl.ProxyAwareImpl;
import ir.amv.os.vaseline.file.business.api.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.dao.basic.api.IVaselineFileDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.blob.IVaselineFileBlobDao;
import ir.amv.os.vaseline.file.dao.def.common.server.dao.base.path.IVaselineFilePathDao;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class TestVaselineFileApiImpl
        extends ProxyAwareImpl
        implements IDefaultVaselineFileApi {
    private List<IVaselineFileDao> fileDaoList;

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

    private static class AuthenticationApiMock
            implements IAuthenticationApi {
        @Override
        public String getCurrentUsername() {
            return "testUser";
        }
    }

    @Override
    public IAuthenticationApi getAuthenticationApi() {
        return new AuthenticationApiMock();
    }

    @Inject
    public void setFileDaoList(List<IVaselineFileDao> fileDaoList) {
        this.fileDaoList = fileDaoList;
    }

}
