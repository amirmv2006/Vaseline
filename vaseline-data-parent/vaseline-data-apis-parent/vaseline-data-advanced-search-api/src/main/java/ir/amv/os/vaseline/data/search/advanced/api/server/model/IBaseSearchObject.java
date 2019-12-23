package ir.amv.os.vaseline.data.search.advanced.api.server.model;

import ir.amv.os.vaseline.data.dao.basic.api.from.SearchJoinType;

/**
 * Created by amv on 12/8/16.
 */
public interface IBaseSearchObject {

    void setJoinType(SearchJoinType joinType);

    SearchJoinType getJoinType();
}
