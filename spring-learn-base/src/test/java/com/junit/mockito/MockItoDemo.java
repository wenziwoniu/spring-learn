package com.junit.mockito;

import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.List;

/**
 * MockIto Demo
 */
public class MockItoDemo {

    @Test
    public void mockItoDemo() {

        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("Hello World");

        System.out.println(list.get(0));
    }

    /**
     * 当Mock一个类或接口时，默认所有的方法也将被mock，如果没有使用when thenReturn指定返回值
     * 方法将会根据返回类型返回一个默认值， 例如0， false等
     */
    @Test
    public void cityMock() {

       CityMockIto cityMockIto = new CityMockIto();
       SchoolMockIto schoolMockIto = mock(SchoolMockIto.class);
       when(schoolMockIto.printSchoolName()).thenReturn("安徽理工大学");
       cityMockIto.printSchoolInfo(schoolMockIto, new ClassMockIto());

    }

    /**
     * 当Spy一个实例时，默认所有的方法都没mock，如果没有使用when thenReturn指定返回值
     * 则调用spy后的实例，则会调用真是的方法
     */
    @Test
    public void citySpyMock() {

        CityMockIto cityMockIto = new CityMockIto();
        SchoolMockIto schoolMockIto = new SchoolMockIto();
        SchoolMockIto spySchool = spy(schoolMockIto);
        // printSchoolName方法会被mock 而printClassCount将调用真是方法
//        when(spySchool.printSchoolName()).thenReturn("安徽理工大学");
        doReturn("安徽理工大学").when(spySchool).printSchoolName();
        cityMockIto.printSchoolInfo(spySchool, new ClassMockIto());

    }

    /**
     * 多层依赖关系的mock
     * CityMockIto -> SchoolMockIto -> ClassMockIto
     * 通过使用spy，可以做到对SchoolMockIto的某些方法进行mock，某些方法进行真实调用
     * 对SchoolMockIto里的ClassMockIto同样可以对方法进行mock或不mock处理
     */
    @Test
    public void multiClassSpyMock() {

        CityMockIto cityMockIto = new CityMockIto();

        SchoolMockIto schoolMockIto = new SchoolMockIto();
        SchoolMockIto spySchool = spy(schoolMockIto);
        // printSchoolName方法会被mock 而printClassCount将调用真是方法
        when(spySchool.printSchoolName()).thenReturn("安徽理工大学");

        ClassMockIto classMockIto = new ClassMockIto();
        ClassMockIto spyClass = spy(classMockIto);
        when(spyClass.getStudentCount()).thenReturn(1000);

        cityMockIto.printSchoolInfo(spySchool, spyClass);

    }

}
