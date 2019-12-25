package ir.amv.os.vaseline.data.advanced.search.maven.plugin.classconverter.impl;

import ir.amv.os.vaseline.data.advanced.search.maven.plugin.fqnconverter.impl.FqnEntityToISOConverterImpl;
import ir.amv.os.vaseline.data.advanced.search.api.model.IBaseSearchObject;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Field;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.Import;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.TypeVariableSource;

import java.util.List;

/**
 * Created by amv on 12/17/16.
 */
public class ClassEntityToISOConverterImpl
        extends BaseClassConverterImpl<JavaClassSource, JavaInterfaceSource> {

    private FqnEntityToISOConverterImpl fqnConverter;
    private String entBasePackage;
    private String isoBasePackage;

    public ClassEntityToISOConverterImpl(String entBasePackage, String isoBasePackage) {
        this.entBasePackage = entBasePackage;
        this.isoBasePackage = isoBasePackage;
        fqnConverter = new FqnEntityToISOConverterImpl(entBasePackage, isoBasePackage);
    }

    @Override
    protected JavaInterfaceSource convertInternal(JavaClassSource source) {
        String destFqn = fqnConverter.convertFqn(source.getQualifiedName());
        String pkg = packageFromFqn(destFqn);
        String className = classNameFromFqn(destFqn);
        JavaInterfaceSource soi = Roaster.create(JavaInterfaceSource.class);
        setParentClass(source, soi);
        soi.setPackage(pkg).setName(className);
        List<TypeVariableSource<JavaClassSource>> typeVariables = source.getTypeVariables();
        for (TypeVariableSource<JavaClassSource> typeVariable : typeVariables) {
            soi.addTypeVariable(typeVariable.getName());
        }
        List<FieldSource<JavaClassSource>> fields = source.getFields();
        for (Field<?> field : fields) {
            addField(soi, field, fqnConverter, entBasePackage);
        }
        // bug fix
        List<Import> imports = soi.getImports();
        for (Import anImport : imports) {
            for (TypeVariableSource<JavaClassSource> typeVariable : typeVariables) {
                if (typeVariable.getName().equals(anImport.getSimpleName())) {
                    soi.removeImport(anImport);
                }
            }
        }
        return soi;
    }

    private void setParentClass(JavaClassSource source, JavaInterfaceSource soi) {
        String superType = source.getSuperType();
        if (!superType.equals(Object.class.getName()) &&
                !superType.matches(".*Base.*Impl<.*>")) { // cause it contains the generics
            String superConverterd;
            if (superType.contains("<")) {
                superConverterd = fqnConverter.convertFqn(superType.substring(0, superType.lastIndexOf('<')));
                superConverterd += superType.substring(superType.lastIndexOf('<'));
            } else {
                superConverterd = fqnConverter.convertFqn(superType);
            }
            // no need in version 2.19.4.Final
//            try {
//                Class<?> sourceClass = Class.forName(source.getQualifiedName());
//                Class<?>[] genericArgumentClasses = ReflectionUtil.getGenericArgumentClassesDeprecated(sourceClass, Class.forName(superType));
//                superConverterd += "<";
//                assert genericArgumentClasses != null;
//                for (Class<?> genericArgumentClass : genericArgumentClasses) {
//                    superConverterd += genericArgumentClass.getName() + ",";
//                }
//                superConverterd = superConverterd.substring(0, superConverterd.length() - 1) + ">";
//            } catch (Exception ignored) {
//            }
            soi.addInterface(superConverterd);
        }
        soi.addInterface(IBaseSearchObject.class);
    }

}
