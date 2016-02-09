package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.base.architecture.server.layers.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileApi extends IBaseCrudApi<IFileEntity, IFileDto, Long> {

    Long uploadFile(IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException;

    void writeFileContent(Long fileId, OutputStream outputStream) throws BaseVaselineServerException;

    IFileEntity createFile() throws BaseVaselineServerException;
}
