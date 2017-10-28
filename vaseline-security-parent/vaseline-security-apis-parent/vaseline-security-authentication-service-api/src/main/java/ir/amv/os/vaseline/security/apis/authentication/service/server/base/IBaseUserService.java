package ir.amv.os.vaseline.security.apis.authentication.service.server.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.service.apis.basic.layer.server.ro.IBaseReadOnlyService;

/**
 * @author Amir
 */
public interface IBaseUserService<UD extends IBaseUserDto>
        extends IBaseReadOnlyService<UD, Long> {

    UD getUserByUsername(String username) throws BaseVaselineClientException;
}
