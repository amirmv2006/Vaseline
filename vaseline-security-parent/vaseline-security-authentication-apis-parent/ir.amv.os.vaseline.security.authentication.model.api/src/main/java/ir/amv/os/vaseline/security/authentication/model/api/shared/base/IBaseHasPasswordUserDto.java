package ir.amv.os.vaseline.security.authentication.model.api.shared.base;

/**
 * @author Amir
 */
public interface IBaseHasPasswordUserDto
        extends IBaseUserDto {
    String getPassword();

    void setPassword(String password);
}
