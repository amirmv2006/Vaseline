package ir.amv.os.vaseline.ws.rest.server.base.ro;

import ir.amv.os.vaseline.base.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.base.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.ws.rest.server.base.parent.IBaseRestService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/13/2016.
 */
public interface IBaseReadOnlyRestService<D extends IBaseDto<Id>, Id extends Serializable>
        extends IBaseRestService {

    D getById(Id id) throws BaseVaselineClientException;

    Long countAll() throws BaseVaselineClientException;

    List<D> getAll() throws BaseVaselineClientException;

    List<D> getAll(PagingDto pagingDto) throws BaseVaselineClientException;


    Long countByExample(D example) throws BaseVaselineClientException;

    List<D> searchByExample(D example) throws BaseVaselineClientException;

    List<D> searchByExample(Map<String, Object> map) throws BaseVaselineClientException;

}
