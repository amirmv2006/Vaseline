package ir.amv.os.vaseline.security.authorization.rbac.auto.user.annot.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Amir
 */
public class AutoUserAnnotActivator implements BundleActivator {

    private AutoUserAddParser autoUserAddParser;

    @Override
    public void start(final BundleContext context) throws Exception {
        autoUserAddParser = new AutoUserAddParser(context);
        autoUserAddParser.open();
    }

    @Override
    public void stop(final BundleContext context) throws Exception {
        autoUserAddParser.close();
        autoUserAddParser = null;
    }
}
