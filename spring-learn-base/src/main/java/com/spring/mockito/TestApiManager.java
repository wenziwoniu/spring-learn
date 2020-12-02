package com.spring.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestApiManager {

    @Autowired
    private OtherService otherService;

    public String aipTest() {

        return "aipTest";
    }

    public String aipTest2() {

        otherService.otherServicePrint();
        return "aipTest2";
    }
}
