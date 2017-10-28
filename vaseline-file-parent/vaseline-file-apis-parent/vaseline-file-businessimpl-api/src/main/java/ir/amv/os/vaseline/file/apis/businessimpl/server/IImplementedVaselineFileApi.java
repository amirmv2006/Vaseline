package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud.IBaseImplementedMultiDaoCrudApi;
import ir.amv.os.vaseline.file.apis.business.server.IVaselineFileApi;
import ir.amv.os.vaseline.file.apis.business.server.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.apis.dao.basic.server.IVaselineFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IImplementedVaselineFileApi
        extends IBaseImplementedMultiDaoCrudApi<IVaselineFileEntity, Long, String, IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto>>, IVaselineFileApi {

    String DEFAULT_CATEGORY = "CATEGORY_NOT_SPECIFIED";

    List<IVaselineFileDaoFinder> getDaoFinderList();
    List<IVaselineFileDao> getFileDaoList();
    IAuthenticationApi getAuthenticationApi();

    @Override
    default Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseVaselineServerException {
        try {
            String category = ((fileCategory == null) || fileCategory.trim().equals("")) ? DEFAULT_CATEGORY : fileCategory;
            IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> dao = this.getDaoFor(category);
            IVaselineFileEntity file = dao.createFile(category);
            file.setFileName(fileName);
            file.setCategory(category);
            file.setContentType(contentType);
            file.setFileSize(fileSize);
            file.setOwner(getAuthenticationApi().getCurrentUsername());
            preSave(file);
            Long fileId = dao.saveFileUsingStream(file, inputStream);
            postSave(file);
            return fileId;
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineServerException {
        try {
            this.getDaoFor(category).writeFileContent(fileId, outputStream);
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    default String getCategoryForEntity(final IVaselineFileEntity entity) throws BaseVaselineServerException {
        return entity.getCategory();
    }

    @Override
    default IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> getDaoFor(String category) throws BaseVaselineServerException {
        return getFileDaoForCategory(category);
    }

    default IVaselineFileDao<IVaselineFileEntity, IVaselineFileDto> getFileDaoForCategory(final String category) {
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
