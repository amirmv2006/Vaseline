package ir.amv.os.vaseline.business.basic.def.server.action;

import ir.amv.os.vaseline.basics.core.api.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.basics.core.api.server.base.exc.notsupported.VaselineFeatureNotSupportedException;
import ir.amv.os.vaseline.business.basic.api.server.action.IBusinessAction;
import ir.amv.os.vaseline.business.basic.api.server.action.metadata.IBusinessMetadata;
import ir.amv.os.vaseline.thirdparty.shared.util.reflection.ReflectionUtil;

import java.lang.reflect.Method;

/**
 * @author Amir
 */
@FunctionalInterface
public interface IDefaultBusinessAction<R>
        extends IBusinessAction<R> {

    @Override
    default Class<?> getRunningClass(){
        throw new VaselineFeatureNotSupportedException();
    }

    @Override
    default Class<R> getActionResultType() {
        Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClasses(getClass(),
                IDefaultBusinessAction.class);
        if (genericArgumentClasses != null) {
            for (Class<?> genericArgumentClass : genericArgumentClasses) {
                if (IBaseEntity.class.isAssignableFrom(genericArgumentClass)) {
                    return (Class<R>) genericArgumentClass;
                }
            }
        }
        return null;
    }

    @Override
    default IBusinessMetadata[] getBusinessMetadata() {
        throw new VaselineFeatureNotSupportedException();
    }

    @Override
    default Method getDeclaredMethod() {
        throw new VaselineFeatureNotSupportedException();
    }

    @Override
    default Object[] getActionParams() {
        throw new VaselineFeatureNotSupportedException();
    }

    @Override
    default String getActionId() {
        throw new VaselineFeatureNotSupportedException();
    }

    @Override
    default String getActionName() {
        throw new VaselineFeatureNotSupportedException();
    }
}
