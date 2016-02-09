package ir.amv.os.vaseline.file.api.server.model.base.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.crud.api.BaseCrudApiImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.file.api.server.model.base.IFileApi;
import ir.amv.os.vaseline.file.api.server.model.base.IFileDao;
import ir.amv.os.vaseline.file.api.server.model.base.IFileEntity;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/9/2016.
 */
@Component
public class FileApiImpl
        extends BaseCrudApiImpl<IFileEntity, IFileDto, Long, IFileDao>
        implements IFileApi {

    @Override
    public Long uploadFile(IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException {
        preSave(fileEntity);
        Long fileId = getDao().saveFileUsingStream(fileEntity, inputStream);
        postSave(fileEntity);
        return fileId;
    }

    @Override
    public void writeFileContent(Long fileId, OutputStream outputStream) throws BaseVaselineServerException {

    }

    @Override
    public IFileEntity createFile() throws BaseVaselineServerException {
        return dao.createFile();
    }
}
