package ir.amv.os.vaseline.file.apis.dao.basic.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.file.apis.model.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileDao<E extends IVaselineFileEntity, D extends IVaselineFileDto>
        extends IBaseCrudDao<E, Long> {

    Long saveFileUsingStream(E fileEntity, InputStream inputStream) throws Exception;

    void writeFileContent(Long fileId, OutputStream outputStream) throws Exception;

    // based on file category, the class to be used will be different
    E createFile(String category) throws BaseVaselineServerException;
}
