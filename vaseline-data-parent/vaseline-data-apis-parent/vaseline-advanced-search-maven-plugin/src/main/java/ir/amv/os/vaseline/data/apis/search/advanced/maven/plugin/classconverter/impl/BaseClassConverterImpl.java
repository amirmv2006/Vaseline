package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.classconverter.impl;

import ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.classconverter.IBaseClassConverter;
import ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.IBaseFqnConverter;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBasePropertyCondition;
import org.jboss.forge.roaster.model.Field;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.GenericCapableSource;
import org.jboss.forge.roaster.model.source.JavaSource;
import org.jboss.forge.roaster.model.source.PropertyHolderSource;
import org.jboss.forge.roaster.model.source.TypeVariableSource;

import java.util.List;

/**
 * Created by amv on 12/17/16.
 */
public abstract class BaseClassConverterImpl<S extends JavaSource<S>, D extends JavaSource<D>>
        implements IBaseClassConverter<S, D> {

    @Override
    public D convert(S source) {
        return convertInternal(source);
    }

    protected abstract D convertInternal(S source);

    protected String packageFromFqn(String destFqn) {
        if (!destFqn.contains(".")) {
            return "";
        }
        return destFqn.substring(0, destFqn.lastIndexOf('.'));
    }

    protected String classNameFromFqn(String destFqn) {
        if (!destFqn.contains(".")) {
            return destFqn;
        }
        return destFqn.substring(destFqn.lastIndexOf('.') + 1);
    }

    protected void addField(PropertyHolderSource<?> soi, Field<?> field, IBaseFqnConverter fqnConverter, String entBasePackage) {
        Type<?> type = field.getType();
        if (type.getTypeArguments() != null && !type.getTypeArguments().isEmpty()) {
            type = type.getTypeArguments().get(0);
        }
        String fieldGenericType = type.getQualifiedName();
        if (!type.isPrimitive()) {
            boolean isRelation = fieldGenericType.startsWith(entBasePackage);
            if (isRelation && soi instanceof GenericCapableSource<?, ?>) {
                List<? extends TypeVariableSource<?>> typeVariables = ((GenericCapableSource<?, ?>) soi).getTypeVariables();
                for (TypeVariableSource<?> typeVariable : typeVariables) {
                    if (type.getName().equals(typeVariable.getName())) {
                        isRelation = false;
                    }
                }
            }
            if (isRelation) {
                soi.addProperty(fqnConverter.convertFqn(fieldGenericType), field.getName());
            } else {
                String propType = IBasePropertyCondition.class.getName() + "<" + fieldGenericType + ">";
                soi.addProperty(propType, field.getName());
            }
        }
    }
}
