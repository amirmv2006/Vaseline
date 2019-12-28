package ir.amv.os.vaseline.service.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.IBaseDto;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.service.basic.api.exc.BaseExternalException;
import ir.amv.os.vaseline.service.basic.api.layer.ro.IBaseReadOnlyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AMV on 2/7/2016.
 */
public interface IDefaultReadOnlyService<
            I extends Serializable,
            D extends IBaseDto<I>,
            M extends IBaseBusinessModel<I>,
            A extends IBaseReadOnlyApi<I, M>
        >
        extends IBaseReadOnlyService<I, D>, IDefaultEntityReadOnlyService<I, D, M, A> {

    @Override
    default D getById(I id) throws BaseExternalException {
        return handleExceptions(() -> {
            M byId = getApi().getById(id);
            return convertFrom(byId, validationGroupsForShow());
        });
    }

    @Override
    default Long countAll() throws BaseExternalException {
        return handleExceptions(() -> getApi().countAll());
    }

    @Override
    default List<D> getAll() throws BaseExternalException {
        return handleExceptions(() -> {
            List<M> all = getApi().getAll();
            return convertFrom(all, validationGroupsForShow());
        });
    }

    @Override
    default Page<D> getAll(Pageable pagingDto) throws BaseExternalException {
        return handleExceptions(() -> {
            Page<M> sortedPaged = getApi().getAll(pagingDto);
            return convertFrom(sortedPaged, validationGroupsForShow());
        });
    }
}
