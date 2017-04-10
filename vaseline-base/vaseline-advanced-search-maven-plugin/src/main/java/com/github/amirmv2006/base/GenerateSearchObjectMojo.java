package com.github.amirmv2006.base;

import com.github.amirmv2006.base.classconverter.impl.ClassEntityToISOConverterImpl;
import com.github.amirmv2006.base.classconverter.impl.ClassEntityToSOImplConverterImpl;
import com.github.amirmv2006.base.fqnconverter.impl.FqnEntityToISOConverterImpl;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaClass;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.JavaSource;

import java.io.File;
import java.io.IOException;

/**
 * Created by amv on 12/12/16.
 */
@Mojo( name = "generate-so", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class GenerateSearchObjectMojo
        extends AbstractMojo {

    @Parameter( defaultValue = "${project.build.sourceDirectory}", property = "generate-so.srcDir", required = true )
    private File srcFolder;

    @Parameter(property = "generate-so.srcEntitiesBasePackage", required = true )
    private String srcEntitiesBasePackage;

    @Parameter( defaultValue = "${project.build.sourceDirectory}", property = "generate-so.destDir", required = true )
    private File destFolder;

    @Parameter(property = "generate-so.destSearchObjectsBasePackage", required = true )
    private String destSearchObjectsBasePackage;

    @Parameter(property = "generate-so.generateSearchObjectInterface", defaultValue = "true", required = true )
    private Boolean generateSearchObjectInterface;

    @Parameter(property = "generate-so.generateSearchObjectImpl", defaultValue = "true", required = true )
    private Boolean generateSearchObjectImpl;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String packagePath = replaceDotWithFileSep(srcEntitiesBasePackage);
        String absolutePath = srcFolder.getAbsolutePath();
        File entitiesBaseFolder = new File(absolutePath + File.separator + packagePath);
        getLog().info("Checking " + entitiesBaseFolder + " recursively");
        generateRecursive(entitiesBaseFolder, "");
    }

    private String replaceDotWithFileSep(String pkg) {
        String separator = File.separator;
        if (separator.equals("\\")) {
            separator = "\\\\";
        }
        return pkg.replaceAll("\\.", separator);
    }

    private void generateRecursive(File entitiesBaseFolder, String subPackage) {
        File[] files = entitiesBaseFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    getLog().info("Checking " + file + " recursively");
                    generateRecursive(file, subPackage + "." + file.getName());
                } else {
                    getLog().info("Generating SearchObject for " + file);
                    generateFile(file, subPackage);
                }
            }
        }
    }

    private void generateFile(File file, String subPackage) {
        try {
            JavaType<?> parse = Roaster.parse(file);
            if (parse instanceof JavaClass<?>) {
                JavaClass<?> javaClass = (JavaClass<?>) parse;
                if (javaClass instanceof JavaClassSource) {
                    JavaClassSource entJavaClassSource = (JavaClassSource) javaClass;
                    if (generateSearchObjectInterface) {
                        JavaInterfaceSource soi = new ClassEntityToISOConverterImpl(
                                srcEntitiesBasePackage,
                                destSearchObjectsBasePackage
                        ).convert(entJavaClassSource);
                        generateFile(soi);
                    }
                    if (generateSearchObjectImpl) {
                        JavaClassSource soImpl = new ClassEntityToSOImplConverterImpl(
                                srcEntitiesBasePackage,
                                generateSearchObjectInterface ? destSearchObjectsBasePackage + ".impl" : destSearchObjectsBasePackage,
                                generateSearchObjectInterface ? new FqnEntityToISOConverterImpl(srcEntitiesBasePackage, destSearchObjectsBasePackage) : null
                        ).convert(entJavaClassSource);
                        generateFile(soImpl);
                    }
                }
            }
        } catch (Exception e) {
            getLog().error("Error parsing file: ", e);
        }
    }

    private void generateFile(JavaSource soi) throws IOException {
        FileUtils.writeStringToFile(new File(
                destFolder.getAbsolutePath() + File.separator + replaceDotWithFileSep(soi.getPackage()) + File.separator + soi.getName() + ".java"
        ), soi.toString().replaceAll("IBasePropertyCondition\\<", "IBasePropertyCondition<?, "), "UTF-8");
    }

    public static void main(String[] args) throws MojoFailureException, MojoExecutionException {
        GenerateSearchObjectMojo mojo = new GenerateSearchObjectMojo();
        mojo.generateSearchObjectImpl = true;
        mojo.generateSearchObjectInterface = true;
        mojo.srcFolder = new File("src/main/java");
        mojo.srcEntitiesBasePackage = "entity";
        mojo.destFolder = new File("src/main/java");
        mojo.destSearchObjectsBasePackage = "so";
        mojo.setLog(new SystemStreamLog());
        mojo.execute();
    }

}
