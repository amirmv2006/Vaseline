package ir.amv.os.vaseline.file.hibernate.spring.server.dao.base;

import ir.amv.os.vaseline.file.apis.daoimpl.hibernate.server.base.blob.IImplementedVaselineFileBlobHibernateDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Amir
 */
public class VaselineFileBlobHibernateDaoImpl
        implements IImplementedVaselineFileBlobHibernateDao {

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
