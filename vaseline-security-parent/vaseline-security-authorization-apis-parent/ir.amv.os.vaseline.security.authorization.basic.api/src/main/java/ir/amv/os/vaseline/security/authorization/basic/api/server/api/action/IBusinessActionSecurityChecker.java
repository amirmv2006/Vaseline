package ir.amv.os.vaseline.security.authorization.basic.api.server.api.action;

/**
 * @author Amir
 */
public interface IBusinessActionSecurityChecker {
    <R> boolean isSecured(MethodExecution methodExecution);

    <R> String getActionTreeName(MethodExecution methodExecution);
}
