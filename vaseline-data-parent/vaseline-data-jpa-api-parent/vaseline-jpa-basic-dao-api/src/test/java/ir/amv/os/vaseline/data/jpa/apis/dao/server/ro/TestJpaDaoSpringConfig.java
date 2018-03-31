package ir.amv.os.vaseline.data.jpa.apis.dao.server.ro;

import ir.amv.os.vaseline.basics.apis.core.server.base.entity.IBaseEntity;
import ir.amv.os.vaseline.data.apis.dao.basic.server.ro.scroller.IVaselineDataScroller;
import ir.amv.os.vaseline.data.jpa.apis.dao.server.ro.vendorspecific.IVendorSpecificDaoHelper;
import ir.amv.os.vaseline.data.test.model.config.TestDataModelJpaConfig;
import ir.amv.os.vaseline.data.test.model.hibernatescroller.TestHibernateDataScroller;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Import(TestDataModelJpaConfig.class)
public class TestJpaDaoSpringConfig {

    @Bean
    ITestCountryDao countryDao() {
        return new TestCountryDaoImpl();
    }

    @Bean
    IVendorSpecificDaoHelper vendorSpecificDaoHelper() {
        return new IVendorSpecificDaoHelper() {
            @Override
            public <E extends IBaseEntity<?>> IVaselineDataScroller<E> scrollQuery(final EntityManager em, final TypedQuery<E> typedQuery) {
                Query hibQuery = typedQuery.unwrap(Query.class);
                ScrollableResults scroll = hibQuery.scroll();
                return new TestHibernateDataScroller<>(scroll);
            }
        };
    }

}
