package com.spring.mockito;

import org.springframework.stereotype.Service;

@Service
public class TestApiDao {

    public String getStuentNameById(long id) {

        return "name is " + id;
    }

    public String getStuentAge() {

        return "age is " + 18;
    }

}
