package ir.amv.os.vaseline.security.osgi.authentication.service.defimpl;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base.VaselineBaseUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.apis.authentication.serviceimpl.server.base.IImplementedBaseUserService;
import ir.amv.os.vaseline.security.osgi.authentication.business.IVaselineBaseUserApi;
import ir.amv.os.vaseline.security.osgi.authentication.service.IVaselineBaseUserService;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.crud.IBaseImplementedCrudService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.validation.Validator;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineBaseUserService.class,
                IBaseUserService.class
        }
)
public class VaselineBaseUserServiceImpl
        implements IVaselineBaseUserService,
        IImplementedBaseUserService<VaselineBaseUserEntity, VaselineBaseUserDto, IVaselineBaseUserApi>,
        IBaseImplementedCrudService<VaselineBaseUserEntity, VaselineBaseUserDto, Long, IVaselineBaseUserApi>{
    private IVaselineBaseUserApi api;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    @Override
    public IVaselineBaseUserApi getApi() {
        return api;
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

    @Reference
    public void setCoreExceptionHandler(final ICoreExceptionHandler coreExceptionHandler) {
        this.coreExceptionHandler = coreExceptionHandler;
    }

    @Reference
    public void setMapper(final IVaselineObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Reference
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

    @Reference
    public void setApi(final IVaselineBaseUserApi api) {
        this.api = api;
    }
}
