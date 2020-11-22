package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiService {
    @Autowired
    private TestApiService testApiService;

    public String test() {

        System.out.println(testApiService.findFromDb());

        String connect = testApiService.connect();
        connect += "test";//test自己的业务
        return connect;
    }

    public String getNameById(long id) {
        return testApiService.getStudentNameByID(id);
    }
}