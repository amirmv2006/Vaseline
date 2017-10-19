package ir.amv.os.vaseline.file.layers.spring.basic.server.service;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.file.apis.business.server.IFileApi;
import ir.amv.os.vaseline.file.apis.serviceimpl.server.IImplementedFileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author Amir
 */
public class FileServiceImpl
        implements IImplementedFileService{

    private IFileApi fileApi;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    @Override
    public IFileApi getApi() {
        return fileApi;
    }

    @Override
    public IVaselineObjectMapper getMapper() {
        return mapper;
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Override
    public ICoreExceptionHandler getCoreExceptionHandler() {
        return coreExceptionHandler;
    }

    @Autowired
    public void setFileApi(final IFileApi fileApi) {
        this.fileApi = fileApi;
    }

    @Autowired
    public void setMapper(final IVaselineObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setCoreExceptionHandler(final ICoreExceptionHandler coreExceptionHandler) {
        this.coreExceptionHandler = coreExceptionHandler;
    }

    @Autowired
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }
}
