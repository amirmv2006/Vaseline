package ir.amv.os.vaseline.data.apis.search.advanced.server.model;

import ir.amv.os.vaseline.data.apis.dao.server.from.SearchJoinType;

/**
 * Created by amv on 12/8/16.
 */
public interface IBaseSearchObject {

    void setJoinType(SearchJoinType joinType);

    SearchJoinType getJoinType();
}
