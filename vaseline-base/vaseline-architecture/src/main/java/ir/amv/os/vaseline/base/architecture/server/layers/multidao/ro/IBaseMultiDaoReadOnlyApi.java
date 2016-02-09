package ir.amv.os.vaseline.base.architecture.server.layers.multidao.ro;

import ir.amv.os.vaseline.base.architecture.server.layers.ent.ro.api.IBaseEntityReadOnlyApi;
import ir.amv.os.vaseline.base.architecture.server.layers.base.ro.dao.IBaseReadOnlyDao;
import ir.amv.os.vaseline.base.core.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.server.base.exc.BaseVaselineServerException;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/9/2016.
 */
public interface IBaseMultiDaoReadOnlyApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(String coreId, Id id) throws BaseVaselineServerException;

    Long countAll(String coreId) throws BaseVaselineServerException;
    List<E> getAll(String coreId) throws BaseVaselineServerException;
    List<E> getAll(String coreId, PagingDto pagingDto) throws BaseVaselineServerException;

    Long countByExample(String coreId, D example) throws BaseVaselineServerException;
    List<E> searchByExample(String coreId, D example) throws BaseVaselineServerException;
    List<E> searchByExample(String coreId, D example, PagingDto pagingDto)
            throws BaseVaselineServerException;

    IBaseReadOnlyDao getDaoFor(String coreId) throws BaseVaselineServerException;
}
