package ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.impl;

import ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.crud.dao.BaseCrudHibernateDaoImpl;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.BaseUserEntity;
import ir.amv.os.vaseline.security.authentication.spring.impl.server.model.user.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.spring.impl.shared.dto.model.user.BaseUserDto;
import org.springframework.stereotype.Repository;

/**
 * Created by AMV on 2/16/2016.
 */
@Repository
public class BaseUserDaoImpl
        extends BaseCrudHibernateDaoImpl<BaseUserEntity, BaseUserDto, Long>
        implements IBaseUserDao {
}
