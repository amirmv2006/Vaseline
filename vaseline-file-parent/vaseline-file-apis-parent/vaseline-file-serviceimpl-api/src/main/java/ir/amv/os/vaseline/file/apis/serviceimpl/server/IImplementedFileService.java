package ir.amv.os.vaseline.file.apis.serviceimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.file.apis.business.server.IFileApi;
import ir.amv.os.vaseline.file.apis.model.server.base.IFileEntity;
import ir.amv.os.vaseline.file.apis.model.shared.IFileDto;
import ir.amv.os.vaseline.file.apis.service.server.IFileService;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.crud.IBaseImplementedCrudService;
import ir.amv.os.vaseline.service.apis.multidao.layerimpl.server.ro.IBaseImplementedMultiDaoReadOnlyService;

import java.io.InputStream;
import java.io.OutputStream;

public interface IImplementedFileService
        extends IFileService,
        IBaseImplementedMultiDaoReadOnlyService<IFileEntity, IFileDto, Long, String, IFileApi>,
        IBaseImplementedCrudService<IFileEntity, IFileDto, Long, IFileApi>{

    @Override
    default Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseVaselineClientException {
        try {
            return getApi().uploadFile(fileName, fileCategory, fileSize, contentType, inputStream);
        } catch (BaseVaselineServerException e) {
            throw convertException(e);
        }
    }

    @Override
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineClientException {
        try {
            getApi().writeFileContent(category, fileId, outputStream);
        } catch (BaseVaselineServerException e) {
            throw convertException(e);
        }
    }
}
