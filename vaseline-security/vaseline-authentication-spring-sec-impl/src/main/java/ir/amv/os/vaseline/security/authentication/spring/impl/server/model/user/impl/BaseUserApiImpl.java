package ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.impl;

import ir.amv.os.vaseline.base.architecture.impl.server.layers.base.crud.api.BaseCrudApiImpl;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.util.hash.HashUtil;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.BaseUserEntity;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.IBaseUserApi;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user.BaseUserDto;
import org.springframework.stereotype.Component;

/**
 * Created by AMV on 2/16/2016.
 */
@Component
public class BaseUserApiImpl
        extends BaseCrudApiImpl<BaseUserEntity, BaseUserDto, Long, IBaseUserDao>
        implements IBaseUserApi {

    // should not be called on update, on user login user is updated, and the password is hashed already!
    private void prepareToStoreUser(BaseUserEntity entity) {
        if (entity.getEnabled() == null) {
            entity.setEnabled(true);
        }
        if (entity.getPassword() != null && !entity.getPassword().equals("")) {
            entity.setPassword(HashUtil.sha1Hash(entity.getPassword()));
        }
    }

    @Override
    public void preSave(BaseUserEntity entity) throws BaseVaselineServerException {
        prepareToStoreUser(entity);
        super.preSave(entity);
    }

}
