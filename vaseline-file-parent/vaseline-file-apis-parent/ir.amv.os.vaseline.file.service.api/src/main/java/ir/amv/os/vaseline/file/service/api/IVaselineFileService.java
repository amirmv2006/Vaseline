package ir.amv.os.vaseline.file.service.api;

import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.file.model.api.shared.base.IVaselineFileDto;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.multidao.api.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.InputStream;
import java.io.OutputStream;

public interface IVaselineFileService
        extends IBaseMultiDaoReadOnlyService<String, Long, IVaselineFileDto>, IBaseCrudService<Long, IVaselineFileDto>{

    Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseVaselineClientException;

    void writeFileContent(String category, Long fileId, OutputStream outputStream) throws BaseVaselineClientException;

}
