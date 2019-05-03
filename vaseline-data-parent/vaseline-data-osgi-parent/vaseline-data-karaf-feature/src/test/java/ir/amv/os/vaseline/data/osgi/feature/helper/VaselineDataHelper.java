package ir.amv.os.vaseline.data.osgi.feature.helper;

import ir.amv.os.vaseline.data.osgi.feature.basic.dao.SampleBasicDao;
import ir.amv.os.vaseline.data.osgi.feature.search.simple.dao.SampleSimpleSearchDao;
import ir.amv.os.vaseline.testing.integration.cucumber.karaf.RemoteObjectFactory;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.osgi.service.coordinator.Coordination;
import org.osgi.service.coordinator.Coordinator;
import org.osgi.service.jdbc.DataSourceFactory;

import javax.transaction.TransactionManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static ir.amv.os.vaseline.testing.integration.cucumber.karaf.helper.KarafOptionsHelper.*;

public class VaselineDataHelper {

    public static void vaselineDataWithPrerequisitesAndTestJpaModel() {
        RemoteObjectFactory.remoteKarafEnvironment.addOptions(defaultOptions());
        Map<String, String> props = new HashMap<>();
        props.put(DataSourceFactory.OSGI_JDBC_DRIVER_CLASS, "org.h2.Driver");
        props.put(DataSourceFactory.JDBC_URL, "jdbc:h2:mem:pax");
        props.put("pool", "aries");
        props.put("xa", "true");
        props.put("aries.xa.exceptionSorter", "none");
        props.put("osgi.jndi.service.name", "jdbc/AmirXADataSource"); // jndi name for aries jndi
        props.forEach((key, value) -> RemoteObjectFactory.remoteKarafEnvironment.addOption(
                KarafDistributionOption.editConfigurationFileExtend("etc/org.ops4j.datasource-testDBBluepring.cfg", key, value)
        ));
        RemoteObjectFactory.remoteKarafEnvironment.addExtraClasses(
                SampleBasicDao.class,
                SampleSimpleSearchDao.class,
                VaselineDataHelper.class,
                ExceptionThrowerSupplier.class);
        RemoteObjectFactory.remoteKarafEnvironment.addOptions(Arrays.asList(
                deployFeature("org.ops4j.pax.jdbc", "pax-jdbc-features", "pax-jdbc,pax-jdbc-config"),
                deployFeature("org.apache.karaf.features", "enterprise", "transaction"),
                deployFeature("org.ops4j.pax.jdbc", "pax-jdbc-features", "pax-jdbc-pool-aries"),
                mavenBundleVersionAsInProject("com.h2database", "h2"),
                mavenBundleVersionAsInProject("org.apache.aries.transaction", "org.apache.aries.transaction.manager"),
                mavenBundleVersionAsInProject("org.apache.aries.transaction", "org.apache.aries.transaction.manager"),
                deployFeature("org.apache.karaf.features", "enterprise", "jpa"),
                deployFeature("com.github.amirmv2006.data.osgi", "vaseline-data-karaf-feature", "hibernate-orm"),
                deployFeature("com.github.amirmv2006.basics.osgi", "vaseline-basics-karaf-feature", "vaseline-basics-karaf-feature"),
                deployFeature("com.github.amirmv2006.data.osgi", "vaseline-data-karaf-feature", "vaseline-data-karaf-feature-jpa"),
                mavenBundleVersionAsInProject("org.apache.geronimo.specs", "geronimo-jpa_2.1_spec"),
                mavenBundleVersionAsInProject("com.github.amirmv2006.data.osgi", "ir.amv.os.vaseline.data.osgi.test.jpa.model")
                ));
    }

    public static <R> R doTransactional(
            TransactionManager tm,
            Coordinator coordinator,
            ExceptionThrowerSupplier<R> supplier) throws Exception {
        tm.begin();
        String coordName = "txCode";
        Coordination coord = coordinator.begin(coordName, 0);
        try {
            R result = supplier.get();
            coord.end();
            tm.commit();
            return result;
        } catch (Exception e) {
            coord.end();
            tm.rollback();
            throw e;
        }
    }

    @FunctionalInterface
    public interface ExceptionThrowerSupplier<R> {
        R get() throws Exception;
    }

}
