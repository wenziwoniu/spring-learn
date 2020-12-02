package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestApiDao {

    @Autowired
    private OtherService otherService;

    public String getStuentNameById(long id) {

        return "name is " + id;
    }

    public String getStuentAge() {

        return "age is " + 18;
    }

    public String getStuentName() {

        return "age is " + "name";
    }

}
