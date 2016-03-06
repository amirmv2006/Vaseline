package ir.amv.os.vaseline.bpm.activiti.engine.impl.config;

import ir.amv.os.vaseline.base.jdbc.config.VaselineJdbcConfig;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.persistence.deploy.Deployer;
import org.activiti.engine.impl.rules.RulesDeployer;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AMV on 3/2/2016.
 */
@Configuration
@ComponentScan(basePackages = "ir.amv.os.vaseline.bpm.activiti.engine.impl.server")
@Import({
        VaselineJdbcConfig.class
})
public class VaselineBpmActivitiConfig
        implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Autowired
    Environment environment;

    @Bean(name = "activitiDbInitializer")
    public DataSourceInitializer activitiDbInitializer(DataSource dataSource) {
        DataSourceInitializer dsInitializer = new DataSourceInitializer();
        dsInitializer.setDataSource(dataSource);
        dsInitializer.setEnabled(environment.acceptsProfiles("initDB"));
        dsInitializer.setDatabasePopulator(activitiDatabasePopulator());
        return dsInitializer;
    }

    @Bean
    public ResourceDatabasePopulator activitiDatabasePopulator() {
        ResourceDatabasePopulator dbPopulator = new ResourceDatabasePopulator();
        dbPopulator.setContinueOnError(true);
        dbPopulator.setIgnoreFailedDrops(true);
        dbPopulator.setSqlScriptEncoding("UTF-8");
        if (isOracle()) {
            dbPopulator.setScripts(
                    new ClassPathResource("/org/activiti/db/drop/activiti.oracle.drop.history.sql"),
                    new ClassPathResource("/org/activiti/db/drop/activiti.oracle.drop.engine.sql"),
                    new ClassPathResource("/org/activiti/db/drop/activiti.mysql.drop.identity.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.oracle.create.identity.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.oracle.create.engine.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.oracle.create.history.sql")
            );
        } else {
            dbPopulator.setScripts(
                    new ClassPathResource("/org/activiti/db/drop/activiti.postgres.drop.history.sql"),
                    new ClassPathResource("/org/activiti/db/drop/activiti.postgres.drop.engine.sql"),
                    new ClassPathResource("/org/activiti/db/drop/activiti.postgres.drop.identity.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.postgres.create.identity.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.postgres.create.engine.sql"),
                    new ClassPathResource("/org/activiti/db/create/activiti.postgres.create.history.sql")
            );

        }
        return dbPopulator;
    }

    private boolean isOracle() {
        String driver = environment
                .getProperty("hibernate.connection.driver_class");
        return driver.contains("oracle");
    }

    @Bean
    @DependsOn("activitiDbInitializer")
    public SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
        processEngineConfiguration.setDataSource(dataSource);
        processEngineConfiguration.setTransactionManager(transactionManager);
        processEngineConfiguration.setDatabaseSchemaUpdate("update");
        processEngineConfiguration.setJobExecutorActivate(false);
//		processEngineConfiguration.setDeploymentResources(deploymentResources);
        List<Deployer> customPostDeployers = new ArrayList<Deployer>();
        customPostDeployers.add(new RulesDeployer());
        processEngineConfiguration.setCustomPostDeployers(customPostDeployers);
        return processEngineConfiguration;
    }

    @Bean(destroyMethod = "close")
    public ProcessEngine processEngine(ProcessEngineConfigurationImpl processEngineConfiguration) throws Exception {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setApplicationContext(applicationContext);
        processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration);
        return processEngineFactoryBean.getObject();
    }

    @Bean
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean
    public ManagementService managementService(ProcessEngine processEngine) {
        return processEngine.getManagementService();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

}
