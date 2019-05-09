package ir.amv.os.vaseline.data.spring.jpa.server.crud;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudDao;
import ir.amv.os.vaseline.data.spring.jpa.server.ro.BaseSpringJpaReadOnlyDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaCrudDaoImpl<I extends Serializable, E extends IBaseEntity<I>>
        extends BaseSpringJpaReadOnlyDaoImpl<I, E>
        implements IDefaultJpaCrudDao<I, E> {
}
