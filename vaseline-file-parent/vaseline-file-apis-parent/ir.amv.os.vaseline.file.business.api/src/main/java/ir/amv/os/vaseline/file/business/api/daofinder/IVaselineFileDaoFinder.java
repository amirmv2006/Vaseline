package ir.amv.os.vaseline.file.business.api.daofinder;

import ir.amv.os.vaseline.file.dao.basic.api.IVaselineFileDao;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;

import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IVaselineFileDaoFinder {

    Integer priority();

    IVaselineFileDao<IVaselineFileBusinessModel, IVaselineFileDto> getDaoFor(String category, List<IVaselineFileDao> fileDaos);

}
