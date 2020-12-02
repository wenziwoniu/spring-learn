package com.junit.mockito;

import com.spring.mockito.ApiService;
import com.spring.mockito.TestApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * MockIto Demo
 * MockitoJUnitRunner需要使用mockito-core【本例使用的是2.15.0版本】里的
 * 如果使用其它jar里的， 注解的对象需要自己进行初始化操作
 */
@RunWith(MockitoJUnitRunner.class)
public class MockItoDemo3 {

    /** 被mock的其它对象，可以被设置到该对象中 */
    @InjectMocks
    private BaseMockService baseMockService;

    @Spy
    @InjectMocks
    private SchoolMockService schoolMockService;

    @Spy
    private ClassesMockService classesMockService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockItoDemo() {

        when(schoolMockService.schoolServiceWithMock()).thenReturn("school mock");
        baseMockService.baseService();

    }

    @Test
    public void mockItoDemo2() {

        when(classesMockService.getClassCount()).thenReturn("classcount mock");
        baseMockService.getClassName();
        baseMockService.getClassCount();

    }


}
