package com.woniu.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * spring + junit Demo
 */
// 指定在单元测试启动的时候创建spring的工厂类对象
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
// RunWith的value属性指定以spring test的SpringJUnit4ClassRunner作为启动类
// 如果不指定启动类，默认启用的junit中的默认启动类
@RunWith(value = SpringJUnit4ClassRunner.class)
public class SpringJunitTestDemo {

    @Autowired
    private SpringBeanTest springBeanTest;

    @Test
    public void test() {

        System.out.println("test");
    }

    @Test
    public void printTest() {

        springBeanTest.print();
    }

}
