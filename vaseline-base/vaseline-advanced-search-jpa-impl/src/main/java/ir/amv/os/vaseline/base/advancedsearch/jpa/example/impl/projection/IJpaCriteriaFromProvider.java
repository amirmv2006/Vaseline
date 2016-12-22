package ir.amv.os.vaseline.base.advancedsearch.jpa.example.impl.projection;

import ir.amv.os.vaseline.base.advancedsearch.api.example.model.SearchJoinType;

import javax.persistence.criteria.From;

/**
 * Created by amv on 12/22/16.
 */
public interface IJpaCriteriaFromProvider {
    From getFrom(String ptn, SearchJoinType joinType);
}
