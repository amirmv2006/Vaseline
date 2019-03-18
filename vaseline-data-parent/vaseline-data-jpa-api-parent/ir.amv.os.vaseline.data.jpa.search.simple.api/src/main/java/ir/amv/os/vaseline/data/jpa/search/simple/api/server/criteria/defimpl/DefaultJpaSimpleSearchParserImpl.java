package ir.amv.os.vaseline.data.jpa.search.simple.api.server.criteria.defimpl;

import ir.amv.os.vaseline.basics.core.api.shared.base.dto.base.IBaseDto;
import ir.amv.os.vaseline.data.jpa.search.simple.api.server.criteria.IBaseJpaSimpleSearchParser;

import java.io.Serializable;

public class DefaultJpaSimpleSearchParserImpl<D extends IBaseDto<Id>, Id extends Serializable>
        implements IBaseJpaSimpleSearchParser<D, Id>{
}
