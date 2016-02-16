package ir.amv.os.vaseline.security.authentication.inapp.server.model.user;

import ir.amv.os.vaseline.base.architecture.server.layers.base.crud.dao.IBaseCrudDao;
import ir.amv.os.vaseline.security.authentication.inapp.shared.dto.model.user.BaseUserDto;

/**
 * Created by AMV on 2/16/2016.
 */
public interface IBaseUserDao
        extends IBaseCrudDao<BaseUserEntity, BaseUserDto, Long> {
}
