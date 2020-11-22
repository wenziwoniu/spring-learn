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
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 多级关系链的调用
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest3 {

    @Autowired
    @InjectMocks
    private ApiService apiService;

    @Spy
    @Autowired
    @InjectMocks
    private TestApiService mockTestApiService;

//    @Mock
    @Spy
    @Autowired
    private TestApiManager testAipManager;

    @Spy
    @Autowired
    private TestApiDao testApiDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
//        ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", mockTestApiService);
    }

    @After
    public void clearMocks() throws Exception {
//        ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", testApiService);
    }

    /**
     * mockTestApiService进行了spy注解 并对connect方法进行的模拟
     * 结果：connect方法按照模拟结果进行了返回，mockTestApiService没有被模拟的方法按照实际代码执行
     */
    @Test
    public void testTestApiService() {

        when(mockTestApiService.connect()).thenReturn("testApiService");
        System.out.println(apiService.test());

    }

    /**
     *
     */
    @Test
    public void testTestApiDao() {

//        when(testAipManager.aipTest()).thenReturn("aichenzhiqing");
        when(testApiDao.getStuentNameById(anyInt())).thenReturn("chenzhiqing");
        System.out.println(apiService.getNameById(1));

    }

}
