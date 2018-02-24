package ir.amv.os.vaseline.security.apis.authentication.serviceimpl.server.base;

import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.security.apis.authentication.business.server.base.IBaseUserApi;
import ir.amv.os.vaseline.security.apis.authentication.model.server.base.IBaseUserEntity;
import ir.amv.os.vaseline.security.apis.authentication.model.shared.base.IBaseUserDto;
import ir.amv.os.vaseline.security.apis.authentication.service.server.base.IBaseUserService;
import ir.amv.os.vaseline.service.apis.basic.layerimpl.server.ro.IBaseImplementedReadOnlyService;

/**
 * @author Amir
 */
public interface IImplementedBaseUserService<U extends IBaseUserEntity, UD extends IBaseUserDto, Api extends
        IBaseUserApi<U>>
        extends IBaseImplementedReadOnlyService<U, UD, Long, Api>, IBaseUserService<UD> {

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
