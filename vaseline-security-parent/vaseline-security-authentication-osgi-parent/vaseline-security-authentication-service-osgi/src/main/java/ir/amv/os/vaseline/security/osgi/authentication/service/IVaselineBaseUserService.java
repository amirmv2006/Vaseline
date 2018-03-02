package ir.amv.os.vaseline.security.osgi.authentication.service;

import ir.amv.os.vaseline.security.apis.authentication.modelimpl.shared.base.VaselineBaseUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.service.apis.basic.layer.server.crud.IBaseCrudService;

/**
 * @author Amir
 */
public interface IVaselineBaseUserService
        extends IBaseUserService<VaselineBaseUserDto>, IBaseCrudService<VaselineBaseUserDto, Long> {
}
