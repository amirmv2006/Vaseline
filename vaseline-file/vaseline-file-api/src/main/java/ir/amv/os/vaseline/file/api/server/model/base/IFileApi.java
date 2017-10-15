package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.business.apis.multidao.server.crud.IBaseMultiDaoCrudApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileApi extends IBaseMultiDaoCrudApi<IFileEntity, IFileDto, Long> {

    String DEFAULT_CATEGORY = "sloppy";

    Long uploadFile(String coreId, IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException;

    void writeFileContent(String coreId, Long fileId, OutputStream outputStream) throws BaseVaselineServerException;

    IFileEntity createFile(String coreId) throws BaseVaselineServerException;
}
