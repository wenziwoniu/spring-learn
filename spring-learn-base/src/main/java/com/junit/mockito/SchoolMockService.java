package com.junit.mockito;

public class SchoolMockService {

    private ClassesMockService classesMockService;

    // 不被mock调
    public String schoolService() {

        return "shcoolService";
    }

    // 被mock调
    public String schoolServiceWithMock() {

        return "shcoolServiceWithMock";
    }

    public String getClassName() {

        return classesMockService.getClassName();
    }

    public String getClassCount() {
        return classesMockService.getClassCount();
    }
}
