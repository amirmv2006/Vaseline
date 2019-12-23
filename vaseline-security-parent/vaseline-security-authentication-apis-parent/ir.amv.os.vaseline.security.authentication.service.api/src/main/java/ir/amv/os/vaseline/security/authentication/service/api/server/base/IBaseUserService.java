package ir.amv.os.vaseline.security.authentication.service.api.server.base;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.security.authentication.model.api.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.service.basic.api.server.ro.IBaseReadOnlyService;

/**
 * @author Amir
 */
public interface IBaseUserService<UD extends IBaseUserDto>
        extends IBaseReadOnlyService<Long, UD> {

    UD loadUserByUsername(String username) throws BaseExternalException;
}
