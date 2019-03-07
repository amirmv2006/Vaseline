package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.data.spring.jpa.server.crud.BaseSpringJpaCrudDaoImpl;
import org.springframework.stereotype.Repository;

/**
 * @author Amir
 */
@Repository
public class RestIntegrationModelDao
        extends BaseSpringJpaCrudDaoImpl<RestIntegrationModelEntity, Long>
        implements IRestIntegrationModelDao {
}
