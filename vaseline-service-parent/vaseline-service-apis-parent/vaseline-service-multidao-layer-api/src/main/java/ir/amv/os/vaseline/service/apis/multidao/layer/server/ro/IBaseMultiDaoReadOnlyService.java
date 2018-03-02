package ir.amv.os.vaseline.service.apis.multidao.layer.server.ro;

import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.apis.core.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.apis.basic.layer.server.base.IBaseService;

import java.io.Serializable;
import java.util.List;

public interface IBaseMultiDaoReadOnlyService<D extends IBaseDto<Id>, Id extends Serializable, Category>
        extends IBaseService {

    D getById(Category category, Id id) throws BaseVaselineClientException;

    Long countAll(Category category) throws BaseVaselineClientException;
    List<D> getAll(Category category) throws BaseVaselineClientException;
    List<D> getAll(Category category, PagingDto pagingDto) throws BaseVaselineClientException;
}
