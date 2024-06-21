package com.timothy.bongsamoa.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(value = TKDatasourceContext.class)
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
        sessionFactory.setConfigLocation(this.applicationContext.getResource("classpath:config/sql-mapper-config.xml"));
        sessionFactory.setMapperLocations(this.applicationContext.getResources("classpath:mapper/*-mapper.xml"));
        return sessionFactory.getObject();
    }
}
