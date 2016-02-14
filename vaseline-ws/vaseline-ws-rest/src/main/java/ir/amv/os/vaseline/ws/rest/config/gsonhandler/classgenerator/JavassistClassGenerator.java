package ir.amv.os.vaseline.ws.rest.config.gsonhandler.classgenerator;

import ir.amv.os.vaseline.ws.rest.server.multiparam.annot.JsonParam;
import javassist.*;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AMV on 2/14/2016.
 */
public class JavassistClassGenerator {

    private JavassistClassGenerator() {
    }

    private static Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

    public static Class<?> getClassForMultiParam(List<JsonParam> params) throws NotFoundException, CannotCompileException, IOException {
        String packageName = "";
        String className = "";
        for (JsonParam param : params) {
            packageName += param.paramName() + ".";
            className += param.paramType().getSimpleName();
        }
        String classQN = packageName + className;
        ClassPool pool = ClassPool.getDefault();
        try {
            pool.get(classQN);
        } catch (NotFoundException e) {
            CtClass cc = pool.makeClass(classQN);
            for (JsonParam param : params) {
                CtField firstName = new CtField(getExistingClass(pool, param.paramType()), param.paramName(), cc);
                firstName.setModifiers(Modifier.PUBLIC);
                cc.addField(firstName);
            }
            cc.writeFile();
            classMap.put(classQN, cc.toClass());
        }
        return classMap.get(classQN);
    }

    private static CtClass getExistingClass(ClassPool pool, Class<?> aClass) throws NotFoundException {
        try {
            return pool.get(aClass.getName());
        } catch (NotFoundException e) {
            pool.insertClassPath(new ClassClassPath(aClass));
            return pool.get(aClass.getName());
        }
    }
}
