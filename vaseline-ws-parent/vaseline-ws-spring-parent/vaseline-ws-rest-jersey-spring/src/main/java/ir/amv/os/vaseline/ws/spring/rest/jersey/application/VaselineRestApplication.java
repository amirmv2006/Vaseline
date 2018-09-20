package ir.amv.os.vaseline.ws.spring.rest.jersey.application;

import ir.amv.os.vaseline.ws.rest.apis.basic.layer.base.IBaseRestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import java.util.List;

/**
 * @author Amir
 */
@ApplicationPath("/rest")
@Component
public class VaselineRestApplication extends ResourceConfig {

    @Autowired
    public void setRestServices(List<IBaseRestService> restServices) {
        restServices.forEach(this::register);
    }

}
