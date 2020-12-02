package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestApiService {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    @Autowired
    private TestApiDao testApiDao;

    @Autowired
    private TestApiManager testApiManager;

    @Autowired
    private OtherService otherService;

    public String connect() {
        System.out.println("connect true result");
        return "connect true result";
    }

    public String findFromDb() {

        System.out.println(testApiDao.getStuentAge());
        System.out.println(testApiDao.getStuentName());

        System.out.println(testApiManager.aipTest());
        System.out.println(testApiManager.aipTest2());

        otherService.otherServicePrint();
        System.out.println("findFromDb true result");
        return "findFromDb true result";
    }

    public String getStudentNameByID(long id) {

        System.out.println(testApiManager.aipTest());
        return testApiDao.getStuentNameById(id);
    }

    public String getStudentAge() {

        return testApiDao.getStuentAge();
    }
}