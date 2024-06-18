package com.timothy.bongsamoa.bean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class TKSQLSessionFactoryBean {
    private static SqlSessionFactory sessionFactory = null;

    static {
        try {
            if (sessionFactory == null) {
                Reader reader = Resources.getResourceAsReader("sql-mapper-config");
                sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static SqlSession getSession() {
        return sessionFactory.openSession();
    }
}
