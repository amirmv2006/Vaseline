package ir.amv.os.vaseline.file.dao.def.hibernate.base.path;

import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class TestFilePathDao
        implements IImplementedVaselineFilePathHibernateDao {
    private SessionFactory sessionFactory;
    private String baseFilePath;

    public TestFilePathDao(String baseFilePath) {
        this.baseFilePath = baseFilePath;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Inject
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String getBaseFilePath() {
        return baseFilePath;
    }
}
