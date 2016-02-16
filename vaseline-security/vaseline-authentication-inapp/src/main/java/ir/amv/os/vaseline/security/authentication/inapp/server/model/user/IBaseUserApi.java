package ir.amv.os.vaseline.security.authentication.inapp.server.model.user;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.api.IBaseCrudApi;
import ir.amv.os.vaseline.security.authentication.inapp.shared.dto.model.user.BaseUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by AMV on 2/16/2016.
 */
public interface IBaseUserApi
        extends IBaseCrudApi<BaseUserEntity, BaseUserDto, Long>, UserDetailsService {
}
