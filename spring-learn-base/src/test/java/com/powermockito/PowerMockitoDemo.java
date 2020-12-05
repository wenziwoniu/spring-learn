package com.powermockito;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.MockitoAnnotations.*;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class PowerMockitoDemo {

    @Mock
    private PowerMockController powerMockController;

    @Before
    public void setUp() {

        initMocks(this);
    }

    @Test
    public void test1() {

        when(powerMockController.getName()).thenReturn("powermock result");
        assertEquals("powermock result", powerMockController.getName());
    }


}
