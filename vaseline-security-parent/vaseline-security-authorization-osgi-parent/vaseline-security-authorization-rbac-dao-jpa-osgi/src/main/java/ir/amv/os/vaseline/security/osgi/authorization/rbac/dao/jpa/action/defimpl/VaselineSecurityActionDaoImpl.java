package ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.defimpl;

import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.security.apis.authentication.modelimpl.server.base.VaselineBaseUserEntity;
import ir.amv.os.vaseline.security.apis.authorization.rbac.daoimpl.jpa.action.IImplementedSecurityActionJpaDao;
import ir.amv.os.vaseline.security.apis.authorization.rbac.modelimpl.server.action.SecurityActionEntity;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.BaseDaoImpl;
import ir.amv.os.vaseline.security.osgi.authorization.rbac.dao.jpa.action.IVaselineSecurityActionDao;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineSecurityActionDao.class
)
public class VaselineSecurityActionDaoImpl
        extends BaseDaoImpl<SecurityActionEntity>
        implements IImplementedSecurityActionJpaDao<SecurityActionEntity>,
        IVaselineSecurityActionDao {

}
