package ir.amv.os.vaseline.service.osgi.feature.search.advanced.layer;

import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.service.search.advanced.api.server.IBaseAdvancedSearchService;

public interface ISampleAdvancedSearchService
        extends IBaseAdvancedSearchService<Long, SampleDto, SampleSearchObject> {
}
