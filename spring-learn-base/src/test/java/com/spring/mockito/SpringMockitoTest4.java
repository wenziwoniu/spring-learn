package com.spring.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 二级关系链的调用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest4 {

    // 如果没有使用@InjectMock 则被mock的字段不会注入到该对象中
    // 如果使用了 @Autowired + @InjectMocks 则被@Mock的字段会设置到本对象中，未设置的则不会
    // 如果使用了 @Autowired + @InjectMocks + @Spy 则被@Mock的字段会设置到本对象中，未进行Mock的字段，使用spring的bean
    @Autowired
    @InjectMocks
    @Spy
    private ApiService apiService;

    // 仅仅使用@Spy 该对象里的字段值是空不会被spring注入
    // 使用@apy和@Autowired 则是mock后的spirng对象，字段里的值是spring bean
//    @Mock
    @Spy
    @Autowired
    private TestApiService testApiService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    // testApiService的其它方法可以正常被mock
    // testApiService的方法可以正常被mock
    @Test
    public void testApiServiceOnlyMock() {

        when(testApiService.findFromDb()).thenReturn("mock findFromDb");
        doReturn("apiMethodWithSpy result").when(apiService).apiMethodWithSpy();
        System.out.println(apiService.testApiServiceMethod());
    }

    // testApiService的其它方法可以正常被mock
    // testApiService的方法可以正常被mock
    @Test
    public void testApiServiceOnlySpy() {

        doReturn("mock findFromDb").when(testApiService).findFromDb();
        System.out.println(apiService.testApiServiceMethod());
    }

}
