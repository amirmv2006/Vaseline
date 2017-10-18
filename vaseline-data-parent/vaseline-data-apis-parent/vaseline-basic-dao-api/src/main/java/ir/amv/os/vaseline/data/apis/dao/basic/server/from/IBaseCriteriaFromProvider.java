package ir.amv.os.vaseline.data.apis.dao.basic.server.from;

/**
 * Created by AMV on 10/4/2017.
 */
public interface IBaseCriteriaFromProvider<Projection> {

    Projection getCriteriaParentProjection(String parentPtn, SearchJoinType joinType);

    default Projection getCriteriaParentProjection(String parentPtn) {
        return getCriteriaParentProjection(parentPtn, null);
    }
}
