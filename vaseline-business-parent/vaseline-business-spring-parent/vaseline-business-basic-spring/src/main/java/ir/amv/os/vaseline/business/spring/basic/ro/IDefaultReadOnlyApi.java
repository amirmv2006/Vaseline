package ir.amv.os.vaseline.business.spring.basic.ro;

import ir.amv.os.vaseline.basics.core.api.crosslayers.converter.api.IModelConverter;
import ir.amv.os.vaseline.basics.core.api.layers.business.model.IBaseBusinessModel;
import ir.amv.os.vaseline.basics.core.api.layers.persistent.model.IBasePersistenceModel;
import ir.amv.os.vaseline.basics.spring.core.utils.reflection.GenericUtils;
import ir.amv.os.vaseline.business.basic.api.exc.BaseBusinessException;
import ir.amv.os.vaseline.business.basic.api.layer.ro.IBaseReadOnlyApi;
import ir.amv.os.vaseline.data.basic.api.base.IBaseReadOnlyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface IDefaultReadOnlyApi<
            I extends Serializable,
            M extends IBaseBusinessModel<I>,
            E extends IBasePersistenceModel<I>,
            R extends IBaseReadOnlyRepository<I, E>
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
    default Page<M> getAll(Pageable pagingDto) throws BaseBusinessException {
        Page<E> allPms = getRepository().findAll(pagingDto);
        Page<M> result = allPms.map(this::convertFrom);
        postGetList(result.getContent());
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
