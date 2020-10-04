package com.woniu.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring启动类
 */
public class HelloWorld {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");

        SpringBeanTest beanTest = (SpringBeanTest) applicationContext.getBean("springBeanTest");
        beanTest.print();

    }
}
