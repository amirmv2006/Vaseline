package ir.amv.os.vaseline.security.spring.authenticationimpl.springsec.config.permissions.condition;

import ir.amv.os.vaseline.security.authentication.spring.sec.api.config.IUserPermissionsProvider;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by AMV on 2/16/2016.
 */
public class NotExistingUserPermissionProvider implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getBeanFactory().getBeansOfType(IUserPermissionsProvider.class).size() == 0;
    }
}
