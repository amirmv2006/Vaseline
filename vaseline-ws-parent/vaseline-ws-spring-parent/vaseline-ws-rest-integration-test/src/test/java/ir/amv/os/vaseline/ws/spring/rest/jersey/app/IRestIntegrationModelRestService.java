package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.ws.rest.apis.basic.layer.crud.IBaseCrudRestService;

import javax.ws.rs.Path;

/**
 * @author Amir
 */
@Path("/integrationTest")
public interface IRestIntegrationModelRestService
        extends IBaseCrudRestService<RestIntegrationModelDto, Long> {
}
