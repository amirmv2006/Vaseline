package ir.amv.os.vaseline.ws.spring.rest.jersey.app;

import ir.amv.os.vaseline.basics.core.api.extsvclayer.model.impl.BaseDtoImpl;

/**
 * @author Amir
 */
public class RestIntegrationModelDto extends BaseDtoImpl<Long> {

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
