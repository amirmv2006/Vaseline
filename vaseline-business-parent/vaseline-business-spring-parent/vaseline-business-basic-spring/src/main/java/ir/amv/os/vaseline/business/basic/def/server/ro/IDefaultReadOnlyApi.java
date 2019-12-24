package ir.amv.os.vaseline.business.basic.def.server.ro;

import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.extsvc.model.impl.paging.PagingDto;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.api.IModelConverter;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.spring.common.repo.SpringRepository;
import ir.amv.os.vaseline.data.spring.common.utils.VaselineSpringUtils;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IDefaultReadOnlyApi<
            I extends Serializable,
            M extends IBaseBusinessModel<I>,
            E extends IBasePersistenceModel<I>,
            R extends SpringRepository<I, E>
        >
        extends IBaseReadOnlyApi<I, M>, IDefaultModelReadOnlyApi<M>, IModelConverter<M, E> {

    R getRepository();

    @Override
    @Transactional
    default M getById(I id) throws BaseBusinessException {
        E pm = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException(""));
        M findById = convertFrom(pm);
        postGet(findById);
        return findById;
    }

    @Override
    @Transactional
    default Long countAll() throws BaseBusinessException {
        return getRepository().count();
    }

    @Override
    @Transactional
    default List<M> getAll() throws BaseBusinessException {
        Iterable<E> allPms = getRepository().findAll();
        List<M> result = new ArrayList<>();
        allPms.forEach(pm -> result.add(convertFrom(pm)));
        postGetList(result);
        return result;
    }

    @Override
    @Transactional
    default List<M> getAll(PagingDto pagingDto) throws BaseBusinessException {
        Page<E> allPms = getRepository().findAll(VaselineSpringUtils.toSpringPageable(pagingDto));
        List<M> result = new ArrayList<>();
        allPms.forEach(pm -> result.add(convertFrom(pm)));
        postGetList(result);
        return result;
    }

    default void postGetList(final List<M> list) throws BaseBusinessException {
        if (list != null) {
            for (M model : list) {
                postGet(model);
            }
        }
    }

    @Override
    default Class<M> getModelClass() {
        return GenericUtils.getGeneric(getClass(), IDefaultReadOnlyApi.class, 1);
    }

}
