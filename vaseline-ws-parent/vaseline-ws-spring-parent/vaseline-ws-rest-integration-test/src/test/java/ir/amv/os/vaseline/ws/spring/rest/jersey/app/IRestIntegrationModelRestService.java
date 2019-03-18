package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.ws.rest.basic.api.crud.IBaseCrudRestService;

import javax.ws.rs.Path;

/**
 * @author Amir
 */
@Path("/integrationTest")
public interface IRestIntegrationModelRestService
        extends IBaseCrudRestService<RestIntegrationModelDto, Long> {
}
