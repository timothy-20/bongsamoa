package com.timothy.bongsamoa.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.timothy.bongsamoa.mapper")
public class TKMapperContext {
    private final ApplicationContext applicationContext;

    @Autowired
    public TKMapperContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSession(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:config/sql-mapper-config.xml"));
        sessionFactory.setMapperLocations(applicationContext.getResource("classpath:mapper/*-mapper.xml"));
        return sessionFactory.getObject();
    }
}
