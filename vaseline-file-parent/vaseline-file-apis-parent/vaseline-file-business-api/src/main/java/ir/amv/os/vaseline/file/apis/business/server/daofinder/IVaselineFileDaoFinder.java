package ir.amv.os.vaseline.file.apis.business.server.daofinder;

import ir.amv.os.vaseline.file.apis.dao.basic.server.IVaselineFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.base.IVaselineFileDto;

import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IVaselineFileDaoFinder {

    Integer priority();

    IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> getDaoFor(String category, List<IVaselineFileDao> fileDaos);

}
