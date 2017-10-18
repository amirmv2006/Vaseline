package ir.amv.os.vaseline.file.apis.dao.server;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.file.apis.model.shared.IFileDto;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileDao<E extends IFileEntity, D extends IFileDto>
        extends IBaseCrudDao<E, Long> {

    Long saveFileUsingStream(E fileEntity, InputStream inputStream) throws Exception;

    void writeFileContent(Long fileId, OutputStream outputStream) throws Exception;
}
