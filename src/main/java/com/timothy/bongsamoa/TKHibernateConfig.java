package com.timothy.bongsamoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.timothy.bongsamoa.entity")
@PropertySource("classpath:application.yml")
public class TKHibernateConfig {
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Value("${spring.url}")
    private String url;

    @Bean
    public DataSource dataSource() {
        Environment env = this.environment;
        String driverClassName1 = this.environment.getProperty("driver-class-name");
        String driverClassName2 = this.environment.getProperty("spring.datasource.driver-class-name");
        String aa = this.url;
        MutablePropertySources propertySources = ((AbstractEnvironment)this.environment).getPropertySources();


        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(this.environment.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(Objects.requireNonNull(this.environment.getProperty("spring.datasource.url")));
        dataSource.setUsername(Objects.requireNonNull(this.environment.getProperty("spring.datasource.username")));
        dataSource.setPassword(Objects.requireNonNull(this.environment.getProperty("spring.datasource.password")));

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.timothy.bongsamoa.entity");
        sessionFactory.setHibernateProperties(this.getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());

        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        return hibernateProperties;
    }
}
