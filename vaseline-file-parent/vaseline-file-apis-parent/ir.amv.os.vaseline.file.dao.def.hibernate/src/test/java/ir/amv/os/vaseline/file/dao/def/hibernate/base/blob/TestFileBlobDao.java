package ir.amv.os.vaseline.file.dao.def.hibernate.base.blob;

import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TestFileBlobDao
        implements IImplementedVaselineFileBlobHibernateDao{
    private SessionFactory sessionFactory;

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Inject
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
