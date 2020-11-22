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
