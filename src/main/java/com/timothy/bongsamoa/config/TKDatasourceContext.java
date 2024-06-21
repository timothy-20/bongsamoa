package com.timothy.bongsamoa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:config/mariadb-config.properties")
public class TKDatasourceContext {
    private final Environment environment;

    @Autowired
    public TKDatasourceContext(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DataSource dataSource() {
        String jdbcURL = Objects.requireNonNull(this.environment.getProperty("jdbc.url"));
        String jdbcUsername = Objects.requireNonNull(this.environment.getProperty("jdbc.username"));
        String jdbcPassword = Objects.requireNonNull(this.environment.getProperty("jdbc.password"));
        String jdbcDriver = Objects.requireNonNull(this.environment.getProperty("jdbc.driver"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource(jdbcURL, jdbcUsername, jdbcPassword);
        dataSource.setDriverClassName(jdbcDriver);
        return dataSource;
    }
}
