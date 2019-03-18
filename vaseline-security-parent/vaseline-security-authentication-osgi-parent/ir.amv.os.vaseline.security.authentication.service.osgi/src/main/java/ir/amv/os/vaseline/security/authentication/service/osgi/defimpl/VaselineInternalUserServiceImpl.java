package ir.amv.os.vaseline.security.authentication.service.osgi.defimpl;

import ir.amv.os.vaseline.basics.core.api.server.base.exc.handler.ICoreExceptionHandler;
import ir.amv.os.vaseline.basics.mapper.api.server.objmapper.IVaselineObjectMapper;
import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineInternalUserEntity;
import ir.amv.os.vaseline.security.authentication.model.def.shared.base.VaselineInternalUserDto;
import ir.amv.os.vaseline.security.authentication.service.api.server.base.IBaseUserService;
import ir.amv.os.vaseline.security.authentication.service.def.server.base.IImplementedBaseUserService;
import ir.amv.os.vaseline.security.authentication.business.osgi.IVaselineInternalUserApi;
import ir.amv.os.vaseline.security.authentication.service.osgi.IVaselineInternalUserService;
import ir.amv.os.vaseline.service.basic.def.server.crud.IBaseImplementedCrudService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.validation.Validator;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = {
                IVaselineInternalUserService.class,
                IBaseUserService.class
        }
)
public class VaselineInternalUserServiceImpl
        implements IVaselineInternalUserService,
        IImplementedBaseUserService<VaselineInternalUserEntity, VaselineInternalUserDto, IVaselineInternalUserApi>,
        IBaseImplementedCrudService<VaselineInternalUserEntity, VaselineInternalUserDto, Long, IVaselineInternalUserApi>{
    private IVaselineInternalUserApi api;
    private IVaselineObjectMapper mapper;
    private Validator validator;
    private ICoreExceptionHandler coreExceptionHandler;

    @Override
    public IVaselineInternalUserApi getApi() {
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
    public void setApi(final IVaselineInternalUserApi api) {
        this.api = api;
    }
}
