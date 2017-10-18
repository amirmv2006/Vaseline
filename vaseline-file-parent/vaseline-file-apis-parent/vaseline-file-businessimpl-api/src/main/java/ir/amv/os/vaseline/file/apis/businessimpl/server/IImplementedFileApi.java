package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud.IBaseImplementedMultiDaoCrudApi;
import ir.amv.os.vaseline.file.apis.business.server.IFileApi;
import ir.amv.os.vaseline.file.apis.business.server.daofinder.IFileDaoFinder;
import ir.amv.os.vaseline.file.apis.dao.server.IFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.IFileDto;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface IImplementedFileApi
        extends IBaseImplementedMultiDaoCrudApi<IFileEntity, Long, String, IFileDao<IFileEntity, IFileDto>>, IFileApi{

    List<IFileDaoFinder> getDaoFinderList();
    List<IFileDao> getFileDaoList();

    @Override
    default Long uploadFile(IFileEntity fileEntity, InputStream inputStream) throws BaseVaselineServerException {
        preSave(fileEntity);
        try {
            Long fileId = getWriteDaoFor(getCategoryForEntity(fileEntity)).saveFileUsingStream(fileEntity, inputStream);
            postSave(fileEntity);
            return fileId;
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineServerException {
        try {
            getWriteDaoFor(category).writeFileContent(fileId, outputStream);
        } catch (Exception e) {
            throw new BaseVaselineServerException(e);
        }
    }

    @Override
    default String getCategoryForEntity(final IFileEntity entity) throws BaseVaselineServerException {
        return entity.getCategory();
    }

    @Override
    default IFileDao<IFileEntity, IFileDto> getReadDaoFor(String category) throws BaseVaselineServerException {
        return getFileDaoForCategory(category);
    }

    @Override
    default IFileDao<IFileEntity, IFileDto> getWriteDaoFor(String category) throws BaseVaselineServerException {
        return getFileDaoForCategory(category);
    }

    default IFileDao<IFileEntity, IFileDto> getFileDaoForCategory(final String category) {
        List<IFileDaoFinder> daoFinderList = new ArrayList<>(getDaoFinderList());
        daoFinderList.sort(Comparator.comparingInt(IFileDaoFinder::priority));
        for (IFileDaoFinder daoRegisterer : daoFinderList) {
            IFileDao<IFileEntity, IFileDto> daoFor = daoRegisterer.getDaoFor(category, getFileDaoList());
            if (daoFor != null) {
                return daoFor;
            }
        }
        return null;
    }

}
