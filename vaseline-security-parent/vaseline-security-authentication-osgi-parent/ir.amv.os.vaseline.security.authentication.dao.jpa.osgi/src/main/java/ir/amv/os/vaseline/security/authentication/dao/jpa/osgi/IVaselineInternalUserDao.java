package ir.amv.os.vaseline.security.authentication.dao.jpa.osgi;

import ir.amv.os.vaseline.data.dao.basic.api.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.authentication.dao.basic.api.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.authentication.model.def.server.base.VaselineInternalUserEntity;

/**
 * @author Amir
 */
public interface IVaselineInternalUserDao
        extends IBaseUserDao<VaselineInternalUserEntity>, IBaseCrudDao<VaselineInternalUserEntity, Long> {
}
