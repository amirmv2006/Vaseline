package ir.amv.os.vaseline.file.apis.service.server;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.file.apis.model.shared.IFileDto;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;
import ir.amv.os.vaseline.service.apis.multidao.layer.server.ro.IBaseMultiDaoReadOnlyService;

import java.io.InputStream;
import java.io.OutputStream;

public interface IFileService
        extends IBaseMultiDaoReadOnlyService<IFileDto, Long, String>, IBaseCrudService<IFileDto, Long>{

    Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType, InputStream inputStream) throws BaseVaselineClientException;

    void writeFileContent(String category, Long fileId, OutputStream outputStream) throws BaseVaselineClientException;

}
