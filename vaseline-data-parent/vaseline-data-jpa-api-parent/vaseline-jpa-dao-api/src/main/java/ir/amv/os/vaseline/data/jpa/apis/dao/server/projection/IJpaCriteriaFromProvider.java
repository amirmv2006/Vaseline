package ir.amv.os.vaseline.data.jpa.apis.dao.server.projection;

import ir.amv.os.vaseline.data.apis.dao.server.from.IBaseCriteriaFromProvider;
import ir.amv.os.vaseline.data.apis.dao.server.from.SearchJoinType;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;

/**
 * Created by amv on 12/22/16.
 */
public interface IJpaCriteriaFromProvider
        extends IBaseCriteriaFromProvider<Path> {
}
