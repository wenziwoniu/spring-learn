package com.spring.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 多级关系链的调用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest3 {

    // 如果仅仅使用@Autowired注解，则该字段是正常的spring bean， 该bean里使用@Autowired注入的字段也是正常的spring bean
    // 如果仅仅使用@InjectMocks 则该字段是被mock生成的对象, 但不能对其方法进行Mock
    // @InjectMocks不可以和@Mock一起使用  和@Spy一起使用时，虽然可以mock返回的结果，但被mock的那个方法会被真实调用

    @Autowired
    @Spy
    private ApiService apiService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    // 如果apiService仅仅使用@Mock注解，则生成的是被Mock成的对象，该对象里面的字段值为null
//    @Test
    public void testOnlyMock() {

        when(apiService.apiMethod()).thenReturn("mock result");
        System.out.println(apiService.apiMethod());


    }

    // 如果apiService仅仅使用@Spy注解，则生成的是被Mock成的对象，该对象里面的字段值为null
    @Test
    public void testOnlySpy() {

//        when(apiService.apiMethodWithSpy()).thenReturn("mock result");
        doReturn("mock result").when(apiService).apiMethodWithSpy();
        System.out.println(apiService.apiMethod());
        System.out.println(apiService.apiMethodWithSpy());

    }

    // 如果apiService使用@Autowired和@Mock注解，则生成的是被Mock成的对象，该对象里面的字段值为null，效果和仅仅使用@Mock相同
    @Test
    public void testWithAutowiredAndMock() {

        when(apiService.apiMethod()).thenReturn("mock result");
        System.out.println(apiService.apiMethod());
        System.out.println(apiService.apiMethodWithSpy());

    }

    // 如果apiService使用@Autowired和@Spy注解，则生成的是Mock后的Spring的对象，该对象里面的字段值为spring注入的真实值
    @Test
    public void testWithAutowiredAndSpy() {

        doReturn("mock result").when(apiService).apiMethodWithSpy();
        System.out.println(apiService.apiMethod());
        System.out.println(apiService.apiMethodWithSpy());

    }

}
