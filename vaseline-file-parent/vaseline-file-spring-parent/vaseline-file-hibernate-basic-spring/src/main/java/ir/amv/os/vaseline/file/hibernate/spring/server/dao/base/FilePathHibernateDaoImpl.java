package ir.amv.os.vaseline.file.hibernate.spring.server.dao.base;

import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.path.IImplementedFilePathHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Amir
 */
public class FilePathHibernateDaoImpl
        implements IImplementedFilePathHibernateDao {

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
