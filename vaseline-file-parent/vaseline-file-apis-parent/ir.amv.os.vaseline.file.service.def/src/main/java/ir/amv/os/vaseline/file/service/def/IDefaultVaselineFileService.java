package ir.amv.os.vaseline.file.service.def;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.file.business.api.IVaselineFileApi;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.file.service.api.IVaselineFileService;
import ir.amv.os.vaseline.service.basic.def.server.crud.IDefaultCrudService;
import ir.amv.os.vaseline.service.multidao.def.server.ro.IDefaultMultiDaoReadOnlyService;

import java.io.InputStream;
import java.io.OutputStream;

public interface IDefaultVaselineFileService
        extends IVaselineFileService,
        IDefaultMultiDaoReadOnlyService<String, Long, IVaselineFileBusinessModel, IVaselineFileDto, IVaselineFileApi>,
        IDefaultCrudService<Long, IVaselineFileBusinessModel, IVaselineFileDto, IVaselineFileApi> {

    @Override
    default Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseExternalException {
        try {
            return getApiProxy().uploadFile(fileName, fileCategory, fileSize, contentType, inputStream);
        } catch (BaseBusinessException e) {
            throw convertException(e);
        }
    }

    @Override
    default void writeFileContent(String category, Long fileId, OutputStream outputStream) throws
            BaseExternalException {
        try {
            getApiProxy().writeFileContent(category, fileId, outputStream);
        } catch (BaseBusinessException e) {
            throw convertException(e);
        }
    }
}
