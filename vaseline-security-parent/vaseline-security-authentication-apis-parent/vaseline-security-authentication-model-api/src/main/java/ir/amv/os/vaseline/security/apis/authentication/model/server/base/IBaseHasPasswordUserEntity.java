package ir.amv.os.vaseline.security.apis.authentication.model.server.base;

/**
 * @author Amir
 */
public interface IBaseHasPasswordUserEntity
        extends IBaseUserEntity {
    String getPassword();
}
