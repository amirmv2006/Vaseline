package ir.amv.os.vaseline.file.apis.business.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileApi extends IBaseMultiDaoReadOnlyApi<IFileEntity, Long, String>, IBaseCrudApi<IFileEntity, Long> {

    String DEFAULT_CATEGORY = "sloppy";

    Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType,
                     InputStream inputStream) throws BaseVaselineServerException;

    void writeFileContent(String category, Long fileId, OutputStream outputStream) throws BaseVaselineServerException;

}
