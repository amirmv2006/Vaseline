package ir.amv.os.vaseline.file.apis.business.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileApi extends IBaseMultiDaoReadOnlyApi<IFileEntity, Long, String> {

    String DEFAULT_CATEGORY = "sloppy";

    Long uploadFile(IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException;

    void writeFileContent(String category, Long fileId, OutputStream outputStream) throws BaseVaselineServerException;

    IFileEntity createFile(String category) throws BaseVaselineServerException;
}
