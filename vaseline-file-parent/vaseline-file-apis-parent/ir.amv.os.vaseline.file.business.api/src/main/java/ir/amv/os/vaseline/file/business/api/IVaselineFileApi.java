package ir.amv.os.vaseline.file.business.api;

import ir.amv.os.vaseline.basics.core.api.bizlayer.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.crud.IBaseCrudApi;
import ir.amv.os.vaseline.business.multidao.api.server.ro.IBaseMultiDaoReadOnlyApi;
import ir.amv.os.vaseline.file.model.api.server.base.IVaselineFileBusinessModel;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by AMV on 2/8/2016.
 */
public interface IVaselineFileApi extends IBaseMultiDaoReadOnlyApi<String, Long, IVaselineFileBusinessModel>, IBaseCrudApi<Long, IVaselineFileBusinessModel> {

    String DEFAULT_CATEGORY = "sloppy";

    Long uploadFile(final String fileName, final String fileCategory, final Long fileSize, final String contentType,
                     InputStream inputStream) throws BaseBusinessException;

    void writeFileContent(String category, Long fileId, OutputStream outputStream) throws BaseBusinessException;

}
