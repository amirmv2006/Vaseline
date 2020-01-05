package ir.amv.os.vaseline.service.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.base.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyService<I extends IBaseBusinessModel<I>, D extends IBaseDto<I>>
        extends IBaseService {

    D getById(I id) throws BaseExternalException;

    Long countAll() throws BaseExternalException;
    List<D> getAll() throws BaseExternalException;
    Page<D> getAll(Pageable pagingDto) throws BaseExternalException;


}
