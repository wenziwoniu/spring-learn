package com.woniu.jdbc.jdbctemplate;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JbcdTemplateTest {

    private JdbcTemplate jdbcTemplate;
    private ApplicationContext context = null;
    private DataSourceTransactionManager dataSourceTransactionManager;
    private DruidDataSource druidDataSource;

    public static void main(String[] args) throws SQLException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");
        JbcdTemplateTest jbcdTemplateTest = new JbcdTemplateTest();
        jbcdTemplateTest.query(applicationContext);
//        jbcdTemplateTest.insert(applicationContext);


    }

    public void query(ApplicationContext applicationContext) {

        druidDataSource = (DruidDataSource) applicationContext.getBean("dataSource");
        System.out.println("druidDataSource：" + druidDataSource.toString());

        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        System.out.println(Thread.currentThread().getName());
        Integer count = jdbcTemplate.queryForObject("select count(*) from test", Integer.class);
        Integer count2 = jdbcTemplate.queryForObject("select count(*) from test", Integer.class);
        System.out.println("count:" + count);

        System.out.println("druidDataSource：" + druidDataSource.toString());

    }

    public void insert(ApplicationContext applicationContext) throws SQLException {


        dataSourceTransactionManager = (DataSourceTransactionManager) applicationContext.getBean("transactionManager");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(def);

        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.execute("INSERT INTO TEST VALUES (1,'WANG')");
//        jdbcTemplate.execute("INSERT INTO TEST VALUES (2,'CHEN234')");

        System.out.println("a");



        dataSourceTransactionManager.commit(transactionStatus);
    }

}
