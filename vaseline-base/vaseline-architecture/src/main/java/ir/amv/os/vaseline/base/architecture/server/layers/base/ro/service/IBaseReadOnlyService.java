package ir.amv.os.vaseline.base.architecture.server.layers.base.ro.service;

import ir.amv.os.vaseline.base.architecture.server.layers.parent.service.IBaseService;
import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseService {

    D getById(Id id) throws BaseVaselineClientException;

    Long countAll() throws BaseVaselineClientException;
    List<D> getAll() throws BaseVaselineClientException;
    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;


    Long countByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example) throws BaseVaselineClientException;
    List<D> searchByExample(D example, PagingDto pagingDto) throws BaseVaselineClientException;

}
