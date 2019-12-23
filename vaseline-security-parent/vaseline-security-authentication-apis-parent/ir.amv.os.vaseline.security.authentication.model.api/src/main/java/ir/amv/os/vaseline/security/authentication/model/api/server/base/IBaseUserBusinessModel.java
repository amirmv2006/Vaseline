package ir.amv.os.vaseline.security.authentication.model.api.server.base;

import ir.amv.os.vaseline.basics.core.api.bizlayer.model.IBaseBusinessModel;

/**
 * @author Amir
 */
public interface IBaseUserBusinessModel
        extends IBaseBusinessModel<Long> {
    String getUsername();
}
