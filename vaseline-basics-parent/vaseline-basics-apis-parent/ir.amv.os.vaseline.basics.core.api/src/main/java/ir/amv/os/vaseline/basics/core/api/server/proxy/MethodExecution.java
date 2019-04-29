package ir.amv.os.vaseline.basics.core.api.server.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author Amir
 */
@Data
@AllArgsConstructor
public class MethodExecution {

    private Object proxy;
    private Object originalObject;
    private Method method;
    private Object[] params;

}
