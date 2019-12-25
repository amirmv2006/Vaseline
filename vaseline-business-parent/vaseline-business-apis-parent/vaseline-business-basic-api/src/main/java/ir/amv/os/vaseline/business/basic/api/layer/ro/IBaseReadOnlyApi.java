package ir.amv.os.vaseline.business.basic.api.layer.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IBaseReadOnlyApi<I extends Serializable, E extends IBaseBusinessModel<I>>
        extends IBaseModelReadApi<E> {

    E getById(I id) throws BaseBusinessException;

    Long countAll() throws BaseBusinessException;
    List<E> getAll() throws BaseBusinessException;
    Page<E> getAll(Pageable pagingDto) throws BaseBusinessException;

}
