package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestApiService {

    @Autowired
    private TestApiDao testApiDao;

    @Autowired
    private TestApiManager testApiManager;

    public String connect() {
        return "error";
    }

    public String findFromDb() {
        return "db_data";
    }

    public String getStudentNameByID(long id) {

        System.out.println(testApiManager.aipTest());
        return testApiDao.getStuentNameById(id);
    }

    public String getStudentAge() {

        return testApiDao.getStuentAge();
    }
}