package ir.amv.os.vaseline.security.osgi.authentication.dao.jpa;

import ir.amv.os.vaseline.data.apis.dao.basic.server.crud.IBaseCrudDao;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.security.apis.authentication.dao.basic.server.base.IBaseUserDao;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;

/**
 * @author Amir
 */
public interface IVaselineBaseUserDao
        extends IBaseUserDao<VaselineBaseUserEntity>, IBaseCrudDao<VaselineBaseUserEntity, Long> {
}
