package ir.amv.os.vaseline.security.authentication.model.api.server.base;

/**
 * @author Amir
 */
public interface IBaseHasPasswordUserBusinessModel
        extends IBaseUserBusinessModel {
    String getPassword();
}
