package ir.amv.os.vaseline.file.business.def;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.multidao.def.server.crud.IDefaultMultiDaoCrudApi;
import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.business.api.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.dao.basic.api.IVaselineFileDao;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileEntity;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.security.authentication.basic.api.server.IAuthenticationApi;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IDefaultVaselineFileApi
        extends IDefaultMultiDaoCrudApi<String, Long, IVaselineFileEntity, IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto>>, IVaselineFileApi {

    String DEFAULT_CATEGORY = "CATEGORY_NOT_SPECIFIED";

    List<IVaselineFileDaoFinder> getDaoFinderList();

    List<IVaselineFileDao> getFileDaoList();

    IAuthenticationApi getAuthenticationApi();

    @Override
    @Transactional
    default Long uploadFile(
            final String fileName,
            final String fileCategory,
            final Long fileSize,
            final String contentType,
            final InputStream inputStream) throws BaseVaselineServerException {
        String category = ((fileCategory == null) || fileCategory.trim().equals("")) ? DEFAULT_CATEGORY : fileCategory;
        IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> dao = getDaoFor(category);
        IVaselineFileEntity file = dao.createFile(category);
        file.setFileName(fileName);
        file.setCategory(category);
        file.setContentType(contentType);
        file.setFileSize(fileSize);
        file.setOwner(getAuthenticationApi().getCurrentUsername());
        preSave(file);
        Long fileId;
        try {
            fileId = dao.saveFileUsingStream(file, inputStream);
        } catch (Exception e) {
            throw new BaseVaselineServerException("Error saving file", e);
        }
        postSave(file);
        return fileId;
    }

    @Override
    @Transactional
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineServerException {
        try {
            this.getDaoFor(category).writeFileContent(fileId, outputStream);
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    default String getCategoryForEntity(final IVaselineFileEntity entity) {
        return entity.getCategory();
    }

    @Override
    default IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> getDaoFor(String category) {
        List<IVaselineFileDaoFinder> daoFinderList = new ArrayList<>(getDaoFinderList());
        daoFinderList.sort(Comparator.comparingInt(IVaselineFileDaoFinder::priority));
        for (IVaselineFileDaoFinder daoRegisterer : daoFinderList) {
            IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> daoFor = daoRegisterer.getDaoFor(category, getFileDaoList());
            if (daoFor != null) {
                return daoFor;
            }
        }
        return null;
    }

}
