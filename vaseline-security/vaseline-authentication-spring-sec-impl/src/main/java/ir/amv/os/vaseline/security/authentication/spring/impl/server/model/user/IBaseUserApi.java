package ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user;

import ir.amv.os.vaseline.business.apis.layer.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user.BaseUserDto;

/**
 * Created by AMV on 2/16/2016.
 */
public interface IBaseUserApi
        extends IBaseCrudApi<BaseUserEntity, BaseUserDto, Long> {
}
