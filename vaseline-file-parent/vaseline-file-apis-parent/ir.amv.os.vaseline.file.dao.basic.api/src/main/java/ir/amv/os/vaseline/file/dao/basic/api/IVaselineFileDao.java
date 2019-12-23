package ir.amv.os.vaseline.file.dao.basic.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileDao<E extends IVaselineFileBusinessModel, D extends IVaselineFileDto>
        extends IBaseCrudDao<Long, E> {

    Long saveFileUsingStream(E fileEntity, InputStream inputStream) throws Exception;

    void writeFileContent(Long fileId, OutputStream outputStream) throws Exception;

    // based on file category, the class to be used will be different
    E createFile(String category) throws BaseBusinessException;
}
