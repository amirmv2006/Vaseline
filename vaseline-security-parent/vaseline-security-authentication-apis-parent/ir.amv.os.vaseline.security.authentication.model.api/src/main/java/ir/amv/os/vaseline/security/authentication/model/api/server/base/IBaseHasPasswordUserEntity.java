package ir.amv.os.vaseline.security.authentication.model.api.server.base;

/**
 * @author Amir
 */
public interface IBaseHasPasswordUserEntity
        extends IBaseUserEntity {
    String getPassword();
}
