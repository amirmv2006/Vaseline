package ir.amv.os.vaseline.data.osgi.feature.search.simple.dao;

import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleDto;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;
import ir.amv.os.vaseline.data.search.simple.api.server.ro.IBaseSimpleSearchDao;

public interface ISampleSimpleSearchDao
        extends IBaseSimpleSearchDao<SampleEntity, SampleDto, Long> {
}
