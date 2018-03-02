package ir.amv.os.vaseline.security.apis.authentication.model.shared.base;

/**
 * @author Amir
 */
public interface IBaseHasPasswordUserDto
        extends IBaseUserDto {
    String getPassword();

    void setPassword(String password);
}
