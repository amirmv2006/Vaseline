package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.basics.apis.dao.server.ent.BaseEntityImpl;

import javax.persistence.Entity;

/**
 * @author Amir
 */
@Entity
public class RestIntegrationModelEntity extends BaseEntityImpl<Long> {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
