package ir.amv.os.vaseline.file.api.server.daoregisterer;

import ir.amv.os.vaseline.file.api.server.model.base.IFileDao;

import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IFileDaoRegisterer {

    Integer priority();

    IFileDao getDaoFor(String category, List<IFileDao> fileDaos);

}
