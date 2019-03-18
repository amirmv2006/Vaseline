package ir.amv.os.vaseline.file.service.def;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileEntity;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import ir.amv.os.vaseline.service.basic.def.server.crud.IBaseImplementedCrudService;
import ir.amv.os.vaseline.service.multidao.def.server.ro.IBaseImplementedMultiDaoReadOnlyService;

import java.io.InputStream;
import java.io.OutputStream;

public interface IImplementedVaselineFileService
        extends IVaselineFileService,
        IBaseImplementedMultiDaoReadOnlyService<IVaselineFileEntity, IVaselineFileDto, Long, String, IVaselineFileApi>,
        IBaseImplementedCrudService<IVaselineFileEntity, IVaselineFileDto, Long, IVaselineFileApi>{

    @Override
    default Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseVaselineClientException {
        try {
            return getApiProxy().uploadFile(fileName, fileCategory, fileSize, contentType, inputStream);
        } catch (BaseVaselineServerException e) {
            throw convertException(e);
        }
    }

    @Override
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseVaselineClientException {
        try {
            getApiProxy().writeFileContent(category, fileId, outputStream);
        } catch (BaseVaselineServerException e) {
            throw convertException(e);
        }
    }
}
