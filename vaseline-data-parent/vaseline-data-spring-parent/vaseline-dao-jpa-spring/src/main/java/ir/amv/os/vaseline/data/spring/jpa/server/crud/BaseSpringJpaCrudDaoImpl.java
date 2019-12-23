package ir.amv.os.vaseline.data.spring.jpa.server.crud;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;
import ir.amv.os.vaseline.data.jpa.dao.basic.api.server.crud.IDefaultJpaCrudRepository;
import ir.amv.os.vaseline.data.spring.jpa.server.ro.BaseSpringJpaPersistentModelDaoImpl;

import java.io.Serializable;

/**
 * @author Amir
 */
public class BaseSpringJpaCrudDaoImpl<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends BaseSpringJpaPersistentModelDaoImpl<I, E>
        implements IDefaultJpaCrudRepository<I, E> {
}
