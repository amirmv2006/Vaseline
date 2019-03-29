package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.startlevel.BundleStartLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.ops4j.pax.exam.CoreOptions.*;

public abstract class PaxExamBaseIntegrationTest {
    private static final String PROP_FRAGMENTS = "iehr.osgi.integrationTest.fragments";
    private static final String PROP_BUNDLES = "iehr.osgi.integrationTest.bundles";
    private static final String PROP_TIMEOUT = "iehr.osgi.integrationTest.paxexam.systemTimeout";
    private static final String PROP_CONFIG_DIR = "iehr.osgi.integrationTest.configDir";
    private Logger logger = LoggerFactory.getLogger(PaxExamBaseIntegrationTest.class);

    @Inject
    protected BundleContext ctx;

    @Configuration
    public Option[] config() {
        List<Option> opts = new ArrayList<Option>();
        String fragments = System.getProperty(PROP_FRAGMENTS);
        String bundles = System.getProperty(PROP_BUNDLES);
        String timeout = System.getProperty(PROP_TIMEOUT);
        String configDir = System.getProperty(PROP_CONFIG_DIR);

        // Load the systemTimeout value, if specified.
        opts.add(getTimeoutOption(System.getProperty(PROP_TIMEOUT)));

        // Load the jUnit bundles
        opts.add(junitBundles());

        // Load all bundle dependencies that are specified in the system
        // property.
        opts.addAll(getITestBundles(bundles));

        opts.addAll(getITestFragments(fragments));
        
        // Set the fileinstall directory to load Configuration Admin data
        opts.add(getFileInstallConfigOption(configDir));

        return opts.toArray(new Option[opts.size()]);
    }

    private Option getTimeoutOption(String timeout) {
        Option opt = null;

        if ((timeout != null) && !"".equals(timeout))
            opt = systemTimeout(Integer.parseInt(timeout));

        return opt;
    }

    private Option getFileInstallConfigOption(String configDir) {
        Option opt = null;

        if ((configDir != null) && !"".equals(configDir))
            opt = frameworkProperty("felix.fileinstall.dir").value(configDir);

        return opt;
    }

    private Collection<Option> getITestBundles(String bundleList) {
        Collection<Option> opts = new HashSet<Option>();

        if ((bundleList != null) && !"".equals(bundleList)) {
            // TODO: validate the string

            for (String bundle : bundleList.split("::")) {
                if (opts.contains(bundle))
                    logger.debug("Loading Bundle --> " + bundle);
                opts.add(bundle("file:" + bundle));
            }
        }

        return opts;
    }
    
    private Collection<Option> getITestFragments(String fragmentList) {
        Collection<Option> opts = new HashSet<Option>();

        if ((fragmentList != null) && !"".equals(fragmentList)) {
            // TODO: validate the string

            for (String fragment : fragmentList.split("::")) {
                if (opts.contains(fragment))
                    logger.debug("Loading fragment --> " + fragment);
                opts.add(bundle("file:" + fragment).noStart());
            }
        }

        return opts;
    }

    public void logOSGIBundles() {
        assertNotNull(ctx);

        int maxLen = 0;
        for (Bundle bundle : ctx.getBundles()) {
            if (bundle.getSymbolicName() != null) {
                String name = bundle.getSymbolicName() + " ("
                        + bundle.getVersion() + ")";
                if (maxLen < name.length())
                    maxLen = name.length();
            }
        }

        StringBuffer dashes = new StringBuffer();
        for (int i = 0; i < maxLen; i++)
            dashes.append("-");
        logger.info("INSTALLED BUNDLES");
        logger.info("-----------------");
        logger.info("Level | Bundle | Bundle State");
        logger.info("--------" + dashes + "------------");
        String format = "%1$5s | %2$-" + maxLen + "s | ";
        for (Bundle bundle : ctx.getBundles()) {
            BundleStartLevel bsl = bundle.adapt(BundleStartLevel.class);
            String b = bundle.getSymbolicName() + " (" + bundle.getVersion()
                    + ")";
            String msg = String.format(format, bsl.getStartLevel(), b)
                    + getBundleStateString(bundle);
            logger.info(msg);
        }
    }

    private String getBundleStateString(Bundle bundle) {
        int state = bundle.getState();
        if (state == Bundle.ACTIVE) {
            return "Active";
        } else if (state == Bundle.INSTALLED) {
            return "Installed";
        } else if (state == Bundle.RESOLVED) {
            return "Resolved";
        } else if (state == Bundle.STARTING) {
            return "Starting";
        } else if (state == Bundle.STOPPING) {
            return "Stopping";
        } else {
            return "Unknown";
        }
    }

    public void logOSGIServices(){
        try {
            ServiceReference<?>[] refs = ctx.getAllServiceReferences(null, null);
            List<String> serviceNames = new ArrayList<String>();
    
            if (refs != null) {
                logger.info("OSGi Services (" + refs.length + ")");
                logger.info("-------------------------------");
                for (ServiceReference<?> r : refs) {
                    Object o = ctx.getService(r);
                    Class<?> c = o.getClass();
                    logger.info("Service: " + c.getName());
                    Type[] intfs = c.getGenericInterfaces();
                    if (intfs.length != 0) {
                        for (Type intf : intfs)
                            logger.info("  - interface: " + intfs.toString());
                    }
                    serviceNames.add(o.getClass().getName());
                    ctx.ungetService(r);
                }

                /*
                logger.info("OSGi Services (" + refs.length + ")");
                logger.info("-------------------------------");
                for (String name : serviceNames) {
                    logger.info("Service: " + name);
                }
                */
            }
        }
        catch (InvalidSyntaxException e) {
            logger.error("logOSGIServices() exception thrown: " + e.getCause());
        }
    }
}