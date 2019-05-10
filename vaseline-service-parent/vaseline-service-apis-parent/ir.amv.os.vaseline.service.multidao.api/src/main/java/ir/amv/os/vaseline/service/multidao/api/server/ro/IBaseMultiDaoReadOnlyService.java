package ir.amv.os.vaseline.service.multidao.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.shared.base.exc.BaseVaselineClientException;
import ir.amv.os.vaseline.service.basic.api.server.base.IBaseService;

import java.io.Serializable;
import java.util.List;

public interface IBaseMultiDaoReadOnlyService<Category, Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseService {

    D getById(Category category, Id id) throws BaseVaselineClientException;

    Long countAll(Category category) throws BaseVaselineClientException;
    List<D> getAll(Category category) throws BaseVaselineClientException;
    List<D> getAll(Category category, PagingDto pagingDto) throws BaseVaselineClientException;
}
