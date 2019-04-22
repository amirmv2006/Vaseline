package ir.amv.os.vaseline.file.hibernate.spring.server.dao.base;

import ir.amv.os.vaseline.file.dao.def.hibernate.base.path.IDefaultVaselineFilePathHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Amir
 */
public class VaselineFilePathHibernateDaoImpl
        implements IDefaultVaselineFilePathHibernateDao {

    @Value("${vaseline.file.path.base}")
    private String vaselineFilePathBase;
    private SessionFactory sessionFactory;

    @Override
    public String getBaseFilePath() {
        return vaselineFilePathBase;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
