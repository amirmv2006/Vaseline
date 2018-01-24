package ir.amv.os.vaseline.file.apis.businessimpl.server;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.AbstractBusinessActionImpl;
import ir.amv.os.vaseline.business.apis.basic.layerimpl.server.action.function.IBusinessFunctionTwo;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * @author Amir
 */
public abstract class UploadFileBusinessFunctionImpl
        extends AbstractBusinessActionImpl<Long> {

    public UploadFileBusinessFunctionImpl(
            final Class<?> runningClass,
            final Method declaredMethod,
            final String fileName,
            final String fileCategory,
            final Long fileSize,
            final String contentType,
            final InputStream inputStream,
            final IBusinessMetadata... businessMetadata) {
        super(UUID.randomUUID().toString(), runningClass, declaredMethod, new Object[]{fileName, fileCategory,
                        fileSize, contentType, inputStream}, businessMetadata);
    }

    @Override
    public Long execute() throws BaseVaselineServerException {
        return innerExecute(
                (String) getActionParams()[0],
                (String) getActionParams()[1],
                (Long) getActionParams()[2],
                (String) getActionParams()[3],
                (InputStream) getActionParams()[4]);
    }

    protected abstract Long innerExecute(
            final String fileName,
            final String fileCategory,
            final Long fileSize,
            final String contentType,
            final InputStream inputStream
            ) throws BaseVaselineServerException;
}
