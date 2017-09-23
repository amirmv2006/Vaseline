package ir.amv.os.vaseline.base.architecture.impl.hibernate.server.layers.ro.dao;

import ir.amv.os.vaseline.base.core.api.server.base.ent.IBaseEntity;
import ir.amv.os.vaseline.base.core.api.shared.base.dto.paging.PagingDto;
import ir.amv.os.vaseline.base.core.api.shared.util.callback.IBaseReturningCallback;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by AMV on 2/24/2016.
 */
public class DefaultPagingResultCreator
        implements IPagingResultCreator {
    @Override
    public <E extends IBaseEntity<?>> List<E> getPagingResult(BaseReadOnlyHibernateDaoImpl<E, ?, ?> dao, IBaseReturningCallback<DetachedCriteria> createCriteriaCallback, PagingDto pagingDto) {
        DetachedCriteria detachedCriteria = createCriteriaCallback.execute();
        Criteria criteria = dao.getCriteriaFromDetachedCriteria(detachedCriteria);
        criteria = dao.paginateCriteria(criteria, pagingDto);
        List<E> listFromCriteria = dao.getListFromCriteria(criteria);
        return listFromCriteria;
    }

}
