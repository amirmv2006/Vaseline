package ir.amv.os.vaseline.business.osgi.feature.search.advanced.layer;

import ir.amv.os.vaseline.business.search.advanced.api.server.IBaseAdvancedSearchApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;

public interface ISampleAdvancedSearchApi extends IBaseAdvancedSearchApi<SampleEntity, SampleSearchObject, Long> {
}
