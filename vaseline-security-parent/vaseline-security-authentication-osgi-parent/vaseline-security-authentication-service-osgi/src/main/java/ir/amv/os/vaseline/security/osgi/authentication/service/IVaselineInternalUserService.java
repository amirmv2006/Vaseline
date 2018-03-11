package ir.amv.os.vaseline.security.osgi.authentication.service;

import ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base.VaselineInternalUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;

/**
 * @author Amir
 */
public interface IVaselineInternalUserService
        extends IBaseUserService<VaselineInternalUserDto>, IBaseCrudService<VaselineInternalUserDto, Long> {
}
