package ir.amv.os.vaseline.security.authentication.service.osgi;

import ir.amv.os.vaseline.security.authentication.model.def.shared.base.VaselineInternalUserDto;
import ir.amv.os.vaseline.security.authentication.service.api.server.base.IBaseUserService;
import ir.amv.os.vaseline.service.basic.api.server.crud.IBaseCrudService;

/**
 * @author Amir
 */
public interface IVaselineInternalUserService
        extends IBaseUserService<VaselineInternalUserDto>, IBaseCrudService<VaselineInternalUserDto, Long> {
}
