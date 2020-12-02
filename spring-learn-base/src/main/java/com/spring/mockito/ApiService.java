package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ApiService {

    public String getServiceName() {
        return serviceName;
    }

    private String serviceName;

    @Autowired
    private TestApiService testApiService;

    @Autowired
    private TestApiManager testApiManager;

    @Autowired
    private TestApiDao testApiDao;

    @Autowired
    private OtherService otherService;

    public String apiMethod() {
        System.out.println("api method called");
        return "apiMethod true result";
    }

    public String apiMethodWithSpy() {
        System.out.println("api method with spy called");
        return "apiMethod with spy true result";
    }


    public String test() {

//        System.out.println(testApiService.findFromDb());
//
//        String connect = testApiService.connect();
//        connect += "test";//test自己的业务
        String connect = "connnect";
        return connect;
    }

    public String testApiServiceMethod() {

        System.out.println(apiMethod());
        System.out.println(apiMethodWithSpy());
        System.out.println(testApiDao.getStuentAge());
        System.out.println(testApiDao.getStuentName());

        System.out.println(testApiManager.aipTest());
        System.out.println(testApiManager.aipTest2());

        otherService.otherServicePrint();

        System.out.println(testApiService.connect());

        return testApiService.findFromDb();

    }

    public String testApiServiceMethodWithMultChian() {

        System.out.println(apiMethod());
        System.out.println(apiMethodWithSpy());
        System.out.println(testApiDao.getStuentAge());
        System.out.println(testApiDao.getStuentName());
        System.out.println(testApiManager.aipTest());
        System.out.println(testApiManager.aipTest2());
        testApiService.connect();
        return testApiService.findFromDb();

    }

    public String findFromDb() {
        return testApiService.findFromDb();
    }

    public String connect() {
        return testApiService.connect();
    }

    public String getNameById(long id) {
//        return testApiService.getStudentNameByID(id);
        return "a";
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}