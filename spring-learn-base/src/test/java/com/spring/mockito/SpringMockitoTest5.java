package com.spring.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 三级关系链的调用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest5 {

    @Autowired
    @InjectMocks
    @Spy
    private ApiService apiService;

    /**
     * 不能 @Autowired+@InjectMocks+@Spy 同时使用，否则报
     * org.mockito.exceptions.misusing.NotAMockException: Argument should be a mock, but is: class com.spring.mockito.TestApiService
     *
     * 同时使用Autowired和spy 则是mock后的spring对象，也会被注入到apiService里， 但testApiService不会被注入被mock的其它字段，使用的是spring bean
     * 同时使用InjectMocks和spy testApiService是mock对象，但不是spring对象， mock的字段可以被注入， 但该字段不会被注入到apiService
     * 同时使用Autowired和InjectMock spring对象，不是mock对象， 会被apiService注入， testApiService会注入其它mock的字段
     * 仅仅使用@InjectMocks 不会被注入到apiService字段
     */
    @Autowired
    @InjectMocks
//    @Spy
    private TestApiService testApiService;

    @Spy
    private TestApiDao testApiDao;

    @Spy
    @Autowired
    private TestApiManager testApiManager;

//    @Autowired
//    private OtherService otherService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    //
    @Test
    public void onlytestApiManager() {

        doReturn("a").when(testApiDao).getStuentName();
//        doReturn("mock findFromDb").when(testApiService).findFromDb();
//        doReturn("apiMethodWithSpy result").when(apiService).apiMethodWithSpy();
        System.out.println(apiService.testApiServiceMethodWithMultChian());
    }

    @Test
    public void onlytestApiDao() {

        doReturn("a").when(testApiDao).getStuentName();
        doReturn("ab").when(testApiManager).aipTest();
//        doReturn("mock findFromDb").when(testApiService).findFromDb();
//        doReturn("apiMethodWithSpy result").when(apiService).apiMethodWithSpy();
        System.out.println(apiService.testApiServiceMethodWithMultChian());
    }
}
