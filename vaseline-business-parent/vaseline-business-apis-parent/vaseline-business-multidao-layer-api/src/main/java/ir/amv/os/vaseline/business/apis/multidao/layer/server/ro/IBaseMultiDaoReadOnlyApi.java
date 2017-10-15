package ir.amv.os.vaseline.business.apis.multidao.layer.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.apis.dao.server.ro.IBaseReadOnlyDao;
import ir.amv.os.vaseline.business.apis.layer.server.ro.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.basics.apis.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.basics.apis.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseMultiDaoReadOnlyApi<E extends IBaseEntity<Id>, Id extends Serializable, Category>
        extends IBaseReadOnlyApi<E, Id> {

    @Override
    default E getById(Id id) throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    E getById(Category category, Id id) throws BaseVaselineServerException;

    @Override
    default Long countAll() throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    Long countAll(Category category) throws BaseVaselineServerException;

    @Override
    default List<E> getAll() throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category) throws BaseVaselineServerException;

    @Override
    default List<E> getAll(PagingDto pagingDto) throws BaseVaselineServerException {
        throw new VaselineFeatureNotSupportedException();
    }
    List<E> getAll(Category category, PagingDto pagingDto) throws BaseVaselineServerException;

}
