package ir.amv.os.vaseline.base.jdbc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by AMV on 2/10/2016.
 */
@Configuration
@PropertySource("classpath:database.properties")
public class VaselineJdbcConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment
                .getProperty("hibernate.connection.driver_class"));
        dataSource.setUrl(environment.getProperty("hibernate.connection.url"));
        dataSource.setUsername(environment
                .getProperty("hibernate.connection.username"));
        dataSource.setPassword(environment
                .getProperty("hibernate.connection.password"));
        return dataSource;
    }

}
