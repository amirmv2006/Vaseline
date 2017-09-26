package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileDao extends IBaseCrudDao<IFileEntity, IFileDto, Long> {

    IFileEntity createFile();

    Long saveFileUsingStream(IFileEntity fileEntity, InputStream inputStream) throws Exception;

    void writeFileContent(Long fileId, OutputStream outputStream) throws Exception;
}
