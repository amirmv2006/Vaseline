package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.VaselineDbOpMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.BusinessFunctionThreeImpl;
import ir.amv.os.vaseline.business.apis.multidao.layerimpl.server.crud.IBaseImplementedMultiDaoCrudApi;
import ir.amv.os.vaseline.file.apis.business.server.IVaselineFileApi;
import ir.amv.os.vaseline.file.apis.business.server.daofinder.IVaselineFileDaoFinder;
import ir.amv.os.vaseline.file.apis.dao.basic.server.IVaselineFileDao;
import ir.amv.os.vaseline.file.apis.model.server.base.IVaselineFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.security.apis.authentication.basic.server.IAuthenticationApi;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
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
    @Transactional
    default Long uploadFile(
            final String fileName,
            final String fileCategory,
            final Long fileSize,
            final String contentType,
            final InputStream inputStream) throws BaseVaselineServerException {
        Method uploadFileMethod = getDeclaredMethod(IImplementedVaselineFileApi.class,
                    "uploadFile", String.class, String.class, Long.class, String.class, InputStream.class);
        return doBusinessAction(new UploadFileBusinessFunctionImpl(
                getClass(), uploadFileMethod, fileName, fileCategory, fileSize, contentType, inputStream, VaselineDbOpMetadata
                .WRITE) {
            @Override
            protected Long innerExecute(
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
        });
    }

    @Override
    @Transactional
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineServerException {
        Method writeFileContentMethod = getDeclaredMethod(IImplementedVaselineFileApi.class,
                    "writeFileContent", String.class, Long.class, OutputStream.class);
        doBusinessAction(new BusinessFunctionThreeImpl<>(
                getClass(), writeFileContentMethod, category, fileId, outputStream, (cat, fId, os)-> {
            try {
                this.getDaoFor(cat).writeFileContent(fId, os);
                return (Void) null;
            } catch (Exception e) {
                throw new BaseVaselineServerException(e);
            }
        }, VaselineDbOpMetadata.WRITE));
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
