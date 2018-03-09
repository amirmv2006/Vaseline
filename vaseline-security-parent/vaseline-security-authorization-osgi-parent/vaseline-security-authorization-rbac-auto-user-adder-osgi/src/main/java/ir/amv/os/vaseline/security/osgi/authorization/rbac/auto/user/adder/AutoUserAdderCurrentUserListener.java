package ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder;

import ir.amv.os.vaseline.security.apis.authentication.basicimpl.listener.ICurrentUserListener;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.auto.user.adder.service.IAuthoUserAdder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Amir
 */
@Component(
        service = {
                ICurrentUserListener.class
        }
)
public class AutoUserAdderCurrentUserListener
        implements ICurrentUserListener{
    private IAuthoUserAdder authoUserAdder;

    @Override
    public void currentUserChanged(final String username) {
        authoUserAdder.currentUserChanged(username);
    }

    @Reference(
            cardinality = ReferenceCardinality.OPTIONAL,
            policyOption = ReferencePolicyOption.GREEDY
    )
    public void setAuthoUserAdder(final IAuthoUserAdder authoUserAdder) {
        this.authoUserAdder = authoUserAdder;
    }
}
