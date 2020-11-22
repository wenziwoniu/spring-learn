package com.junit.mockito;

public class SchoolMockIto {

    public String printSchoolName() {

        return "school name is aust";
    }

    public Integer printClassCount() {

        return 100;
    }

    public String getClassName(ClassMockIto classMockIto) {

        return classMockIto.getClassName();
    }

    public Integer getStudentCount(ClassMockIto classMockIto) {

        return classMockIto.getStudentCount();
    }
}
