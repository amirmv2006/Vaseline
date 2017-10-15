package ir.amv.os.vaseline.business.apis.multidao.server.ro;

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
public interface IBaseMultiDaoReadOnlyApi<E extends IBaseEntity<Id>, D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseEntityReadOnlyApi<E> {

    E getById(String category, Id id) throws BaseVaselineServerException;

    Long countAll(String category) throws BaseVaselineServerException;
    List<E> getAll(String category) throws BaseVaselineServerException;
    List<E> getAll(String category, PagingDto pagingDto) throws BaseVaselineServerException;

    Long countByExample(String category, D example) throws BaseVaselineServerException;
    List<E> searchByExample(String category, D example) throws BaseVaselineServerException;
    List<E> searchByExample(String category, D example, PagingDto pagingDto)
            throws BaseVaselineServerException;

    IBaseReadOnlyDao getDaoFor(String category) throws BaseVaselineServerException;
}
