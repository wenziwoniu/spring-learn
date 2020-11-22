package com.spring.mockito;

import com.AopTargetUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest2 {

    @Autowired
    @InjectMocks
    private ApiService apiService;

    @Mock
    private TestApiService mockTestApiService;

    @Autowired
    private TestApiService testApiService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
//        ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", mockTestApiService);
    }

    @After
    public void clearMocks() throws Exception {
//        ReflectionTestUtils.setField(AopTargetUtils.getTarget(apiService), "testApiService", testApiService);
    }

    @Test
    public void should_success_when_testApiService() {

        when(mockTestApiService.connect()).thenReturn("ok");
        String result = apiService.test();
        Assert.assertEquals("oktest", result);
    }

}
