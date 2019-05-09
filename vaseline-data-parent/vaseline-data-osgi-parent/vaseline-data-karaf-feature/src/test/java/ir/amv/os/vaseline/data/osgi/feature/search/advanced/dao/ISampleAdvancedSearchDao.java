package ir.amv.os.vaseline.data.osgi.feature.search.advanced.dao;

import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleSearchObject;
import ir.amv.os.vaseline.data.search.advanced.api.server.ro.IBaseAdvancedSearchDao;

public interface ISampleAdvancedSearchDao
        extends IBaseAdvancedSearchDao<Long, SampleEntity, SampleSearchObject> {
}
