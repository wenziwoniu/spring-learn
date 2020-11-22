package com.junit.mockito;

public class CityMockIto {

    public void printSchoolInfo(SchoolMockIto schoolMockIto, ClassMockIto classMockIto) {

        System.out.println(schoolMockIto.printSchoolName());
        System.out.println(schoolMockIto.printClassCount());
        System.out.println(schoolMockIto.getClassName(classMockIto));
        System.out.println(schoolMockIto.getStudentCount(classMockIto));
    }
}
