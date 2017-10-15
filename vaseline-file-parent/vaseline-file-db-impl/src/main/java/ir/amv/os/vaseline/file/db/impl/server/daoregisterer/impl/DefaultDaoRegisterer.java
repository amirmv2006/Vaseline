package ir.amv.os.vaseline.file.db.impl.server.daoregisterer.impl;

import ir.amv.os.vaseline.file.api.server.daoregisterer.IFileDaoRegisterer;
import ir.amv.os.vaseline.file.api.server.model.base.IFileDao;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
@Component
public class DefaultDaoRegisterer implements IFileDaoRegisterer {
    @Override
    public Integer priority() {
        return 0;
    }

    @Override
    public IFileDao getDaoFor(String category, List<IFileDao> fileDaos) {
        return fileDaos.get(0);
    }
}
