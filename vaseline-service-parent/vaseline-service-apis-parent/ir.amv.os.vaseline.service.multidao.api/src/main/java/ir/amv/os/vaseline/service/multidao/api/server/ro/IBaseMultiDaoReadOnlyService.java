package ir.amv.os.vaseline.service.multidao.api.server.ro;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.IBaseDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.extsvclayer.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.server.base.IBaseService;

import java.io.Serializable;
import java.util.List;

public interface IBaseMultiDaoReadOnlyService<Category, Id extends Serializable, D extends IBaseDto<Id>>
        extends IBaseService {

    D getById(Category category, Id id) throws BaseExternalException;

    Long countAll(Category category) throws BaseExternalException;
    List<D> getAll(Category category) throws BaseExternalException;
    List<D> getAll(Category category, PagingDto pagingDto) throws BaseExternalException;
}
