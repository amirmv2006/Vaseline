package ir.amv.os.vaseline.file.api.server.model.base;

import ir.amv.os.vaseline.base.architecture.server.layers.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.file.api.shared.model.base.IFileDto;

import java.io.InputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IFileDao extends IBaseCrudDao<IFileEntity, IFileDto, Long> {

    IFileEntity createFile();

    Long saveFileUsingStream(IFileEntity fileEntity, InputStream inputStream);
}
