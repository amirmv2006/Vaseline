package ir.amv.os.vaseline.business.osgi.feature.search.simple.layer;

import ir.amv.os.vaseline.business.search.simple.api.server.IBaseSimpleSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

public interface ISampleSimpleSearchApi extends IBaseSimpleSearchApi<Long, SampleEntity, SampleDto> {
}
