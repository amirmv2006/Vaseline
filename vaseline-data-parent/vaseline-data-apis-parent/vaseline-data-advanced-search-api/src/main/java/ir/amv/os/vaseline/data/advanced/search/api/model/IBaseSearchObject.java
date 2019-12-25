package ir.amv.os.vaseline.data.advanced.search.api.model;

import ir.amv.os.vaseline.data.basic.api.from.SearchJoinType;

/**
 * Created by amv on 12/8/16.
 */
public interface IBaseSearchObject {

    void setJoinType(SearchJoinType joinType);

    SearchJoinType getJoinType();
}
