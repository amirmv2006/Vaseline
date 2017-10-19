package ir.amv.os.vaseline.file.hibernate.spring.server.dao.base;

import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob.IImplementedFileBlobHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Amir
 */
public class FileBlobHibernateDaoImpl
        implements IImplementedFileBlobHibernateDao {

    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
