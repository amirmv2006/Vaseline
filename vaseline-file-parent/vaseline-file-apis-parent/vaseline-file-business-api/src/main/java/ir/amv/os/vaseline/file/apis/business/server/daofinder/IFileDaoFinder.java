package ir.amv.os.vaseline.file.apis.business.server.daofinder;

import ir.amv.os.vaseline.file.apis.dao.server.IFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.IFileDto;

import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IFileDaoFinder {

    Integer priority();

    IFileDao<IFileEntity, IFileDto> getDaoFor(String category, List<IFileDao> fileDaos);

}
