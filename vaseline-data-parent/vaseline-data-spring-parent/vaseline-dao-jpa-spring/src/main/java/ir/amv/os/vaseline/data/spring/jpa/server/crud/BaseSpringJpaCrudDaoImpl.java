package ir.amv.os.vaseline.data.spring.jpa.server.crud;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.crud.IBaseImplementedJpaCrudDao;
import ir.amv.os.vaseline.data.spring.jpa.server.ro.BaseSpringJpaReadOnlyDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaCrudDaoImpl<E extends IBaseEntity<Id>, Id extends Serializable>
        extends BaseSpringJpaReadOnlyDaoImpl<E, Id>
        implements IBaseImplementedJpaCrudDao<E, Id> {
}
