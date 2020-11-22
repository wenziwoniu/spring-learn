package com.spring.mockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * 对对象的方法直接mock
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class SpringMockitoTest {

    @Mock
    private ApiService mockApiService;

    @Before
    public void init() {
        initMocks(this);
    }

    @Test
    public void should_success_when_testApiService() {

        when(mockApiService.test()).thenReturn("ok");
        String result = mockApiService.test();
        Assert.assertEquals("ok", result);
    }

}
