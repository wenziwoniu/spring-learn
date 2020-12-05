package com.powermockito;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PrepareOnlyThisForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {PowerMockService.class, PowerMockController.class, DirectoryStructure.class})
public class PowerMockitoDemo2 {

    // 使用PowerMockRunner时，不能使用@Spy注解， 但使用MockitoJunitRunner时，可以使用该注解
//    @Spy
    @InjectMocks
    private PowerMockController powerMockController;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testForPrivate() throws Exception {

        PowerMockController spyController = PowerMockito.spy(powerMockController);

        // 对私有方法进行测试，返回的私有方法的返回结果
        Object object = Whitebox.invokeMethod(powerMockController, "getCount");

        PowerMockito.doReturn("a").when(spyController,"getCount");
        Assert.assertEquals("a", spyController.getStudentCount());
    }

    @Test
    public void testForStatic() throws Exception {

        PowerMockito.mockStatic(PowerMockService.class);
        when(PowerMockService.generateId()).thenReturn("mock result");
        Assert.assertEquals("mock result", powerMockController.getId());
    }

    @Test
    public void testForConstrut() throws Exception {

        final String directoryPath = "mocked path";

        File directoryMock = mock(File.class);

        // This is how you tell PowerMockito to mock construction of a new File.
        whenNew(File.class).withArguments(directoryPath).thenReturn(directoryMock);

        // Standard expectations
        when(directoryMock.exists()).thenReturn(false);
        when(directoryMock.mkdirs()).thenReturn(true);

        assertTrue(new DirectoryStructure().create(directoryPath));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForConstrut2() throws Exception {

        assertTrue(new DirectoryStructure().create("D:\\spring-learn.zip"));
    }


}
