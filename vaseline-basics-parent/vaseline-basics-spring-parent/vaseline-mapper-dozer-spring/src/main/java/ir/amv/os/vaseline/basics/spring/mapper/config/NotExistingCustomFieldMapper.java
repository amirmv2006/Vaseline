package ir.amv.os.vaseline.basics.spring.mapper.config;

import org.dozer.CustomFieldMapper;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by AMV on 2/14/2016.
 */
public class NotExistingCustomFieldMapper implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getBeanFactory().getBeansOfType(CustomFieldMapper.class).size() == 0;
    }
}
