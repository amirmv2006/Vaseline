package ir.amv.os.vaseline.file.layers.spring.basic.server.service;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.file.apis.business.server.IVaselineFileApi;
import ir.amv.os.vaseline.file.apis.serviceimpl.server.IImplementedVaselineFileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * @author Amir
 */
public class VaselineFileServiceImpl
        implements IImplementedVaselineFileService {

    private IVaselineFileApi fileApi;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    @Override
    public IVaselineFileApi getApi() {
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
    public void setFileApi(final IVaselineFileApi fileApi) {
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
