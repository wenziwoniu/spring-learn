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

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * MockIto Demo
 * MockitoJUnitRunner需要使用mockito-core【本例使用的是2.15.0版本】里的
 * 如果使用其它jar里的， 注解的对象需要自己进行初始化操作
 */
@RunWith(MockitoJUnitRunner.class)
public class MockItoDemo2 {

    @InjectMocks
    private ApiService apiService;

    @Spy
    private TestApiService testApiService;

    @Before
    public void setUp() {
        System.out.println("a");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockItoDemo() {

        when(testApiService.connect()).thenReturn("hello mockito");
        System.out.println(apiService.test());

    }


}
