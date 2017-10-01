package ir.amv.os.vaseline.data.hibernate.apis.dao.hibernatepatch;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.TypedValue;

public class StringFunctionCriterion implements Criterion {

    public enum StringFunction {
        LOWER,
        UPPER;

        public String apply(String column, SessionFactoryImplementor factory) {
            switch (this) {
                case LOWER:
                    return factory.getServiceRegistry().getService(JdbcServices.class).getDialect().getLowercaseFunction() + "(" + column + ")";
                case UPPER:
                    return factory.getServiceRegistry().getService(JdbcServices.class).getDialect().getFunctions().get("upper") + "(" + column + ")";
            }
            return column;
        }
    }

    private String propertyName;
    private StringFunction function;
    private Criterion wrapped;

    public StringFunctionCriterion(String propertyName, StringFunction function, Criterion wrapped) {
        this.propertyName = propertyName;
        this.function = function;
        this.wrapped = wrapped;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        String originalSql = wrapped.toSqlString(criteria, criteriaQuery);
        final String[] columns = criteriaQuery.findColumns( propertyName, criteria );
        final SessionFactoryImplementor factory = criteriaQuery.getFactory();
        if (columns != null) {
            for (String column : columns) {
                originalSql = originalSql.replaceFirst(column, function.apply(column, factory));
            }
        }
        return originalSql;
    }

    @Override
    public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        return wrapped.getTypedValues(criteria, criteriaQuery);
    }
}
