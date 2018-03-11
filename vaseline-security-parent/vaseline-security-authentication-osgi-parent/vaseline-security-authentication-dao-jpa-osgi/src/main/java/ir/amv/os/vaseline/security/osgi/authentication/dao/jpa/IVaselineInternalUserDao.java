package ir.amv.os.vaseline.security.osgi.authentication.dao.jpa;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineInternalUserEntity;

/**
 * @author Amir
 */
public interface IVaselineInternalUserDao
        extends IBaseUserDao<VaselineInternalUserEntity>, IBaseCrudDao<VaselineInternalUserEntity, Long> {
}
