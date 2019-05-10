package ir.amv.os.vaseline.business.osgi.feature.basic.layer;

import ir.amv.os.vaseline.business.basic.api.server.crud.IBaseCrudApi;
import ir.amv.os.vaseline.data.osgi.test.jpa.model.SampleEntity;

public interface ISampleBasicApi extends IBaseCrudApi<Long, SampleEntity> {
}
