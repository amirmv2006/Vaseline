package ir.amv.os.vaseline.data.search.advanced.api.server.model;

import ir.amv.os.vaseline.data.dao.basic.api.from.SearchJoinType;

/**
 * Created by amv on 12/22/16.
 */
public class BaseSearchObjectImpl
        implements IBaseSearchObject {
    private SearchJoinType joinType;

    @Override
    public void setJoinType(SearchJoinType joinType) {
        this.joinType = joinType;
    }

    @Override
    public SearchJoinType getJoinType() {
        return joinType;
    }
}
