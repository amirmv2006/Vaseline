package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.classconverter.impl;

import ir.amv.os.vaseline.basics.dao.api.server.ent.BaseEntityImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.impl.FqnEntityToISOConverterImpl;
import ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.impl.FqnEntityToSOImplConverterImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.BaseSearchObjectImpl;
import ir.amv.os.vaseline.data.search.advanced.api.server.model.IBaseSearchObject;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Field;
import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.Import;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.TypeVariableSource;

import java.util.List;

/**
 * Created by amv on 12/17/16.
 */
public class ClassEntityToSOImplConverterImpl
        extends BaseClassConverterImpl<JavaClassSource, JavaClassSource> {

    private FqnEntityToSOImplConverterImpl soImplConverter;
    private FqnEntityToISOConverterImpl isoConverter;
    private String entBasePackage;
    private String isoBasePackage;

    public ClassEntityToSOImplConverterImpl(String entBasePackage, String isoBasePackage, FqnEntityToISOConverterImpl isoConverter) {
        this.entBasePackage = entBasePackage;
        this.isoBasePackage = isoBasePackage;
        soImplConverter = new FqnEntityToSOImplConverterImpl(entBasePackage, isoBasePackage);
        this.isoConverter = isoConverter;
    }

    @Override
    protected JavaClassSource convertInternal(JavaClassSource source) {
        String destFqn = soImplConverter.convertFqn(source.getQualifiedName());
        String pkg = packageFromFqn(destFqn);
        String className = classNameFromFqn(destFqn);
        JavaClassSource soi = Roaster.create(JavaClassSource.class);
        setParentClass(source, soi);
        soi.setPackage(pkg).setName(className);
        List<TypeVariableSource<JavaClassSource>> typeVariables = source.getTypeVariables();
        for (TypeVariableSource<JavaClassSource> typeVariable : typeVariables) {
            soi.addTypeVariable(typeVariable.getName());
        }
        List<FieldSource<JavaClassSource>> fields = source.getFields();
        for (Field<?> field : fields) {
            addField(soi, field, isoConverter, entBasePackage);

        }
        if (isoConverter != null) {
            String isoFqn = isoConverter.convertFqn(source.getQualifiedName());
            if (!typeVariables.isEmpty()) {
                isoFqn += "<";
                for (TypeVariableSource<JavaClassSource> typeVariable : typeVariables) {
                    isoFqn += typeVariable.getName() + ",";
                }
                isoFqn = isoFqn.substring(0, isoFqn.length() - 1) + ">";
            }
            soi.addInterface(isoFqn);
        } else {
            soi.addInterface(IBaseSearchObject.class);
        }
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

    private void setParentClass(JavaClassSource source, JavaClassSource soi) {
        String superType = source.getSuperType();
        if (!superType.equals(Object.class.getName()) && !superType.startsWith(BaseEntityImpl.class.getName())) { // startsWith cause it contains the generics
            String superConverterd;
            if (superType.contains("<")) {
                superConverterd = soImplConverter.convertFqn(superType.substring(0, superType.lastIndexOf('<')));
                superConverterd += superType.substring(superType.lastIndexOf('<'));
            } else {
                superConverterd = soImplConverter.convertFqn(superType);
            }
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
            soi.setSuperType(superConverterd);
        } else {
            soi.setSuperType(BaseSearchObjectImpl.class);
        }
    }

}
