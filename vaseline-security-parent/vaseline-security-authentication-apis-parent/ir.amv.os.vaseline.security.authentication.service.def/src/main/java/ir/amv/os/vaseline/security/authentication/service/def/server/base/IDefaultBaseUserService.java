package ir.amv.os.vaseline.security.authentication.service.def.server.base;

import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.security.authentication.business.api.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.model.api.server.base.IBaseUserEntity;
import ir.amv.os.vaseline.security.authentication.model.api.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.security.authentication.service.api.server.base.IBaseUserService;
import ir.amv.os.vaseline.service.basic.def.server.ro.IDefaultReadOnlyService;

/**
 * @author Amir
 */
public interface IDefaultBaseUserService<U extends IBaseUserEntity, UD extends IBaseUserDto, Api extends
        IBaseUserApi<U>>
        extends IDefaultReadOnlyService<Long, U, UD, Api>, IBaseUserService<UD> {

    @Override
    default UD loadUserByUsername(String username) throws BaseVaselineClientException {
        try {
            U byId = getApiProxy().loadUserByUsername(username);
            return convertEntityToDTO(byId, validationGroupsForShow());
        } catch (Exception e) {
            throw convertException(e);
        }
    }
}
