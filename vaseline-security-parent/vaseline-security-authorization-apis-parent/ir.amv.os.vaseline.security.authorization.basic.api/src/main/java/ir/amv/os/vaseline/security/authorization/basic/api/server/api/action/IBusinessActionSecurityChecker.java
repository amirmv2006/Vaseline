package ir.amv.os.vaseline.security.authorization.basic.api.server.api.action;

import ir.amv.os.vaseline.basics.core.api.server.proxy.MethodExecution;

/**
 * @author Amir
 */
public interface IBusinessActionSecurityChecker {
    <R> boolean isSecured(MethodExecution methodExecution);

    <R> String getActionTreeName(MethodExecution methodExecution);
}
