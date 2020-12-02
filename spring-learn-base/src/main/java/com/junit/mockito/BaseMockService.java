package com.junit.mockito;

public class BaseMockService {

    private SchoolMockService schoolMockService;

    public void baseService() {

        System.out.println("baseService");
        System.out.println(schoolMockService.schoolService());
        System.out.println(schoolMockService.schoolServiceWithMock());
    }

    public String getClassName() {

        String name = schoolMockService.getClassName();
        System.out.println(name);
        return name;
    }

    public String getClassCount() {

        String name = schoolMockService.getClassCount();
        System.out.println(name);
        return name;
    }

}
