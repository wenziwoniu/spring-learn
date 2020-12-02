package com.spring.mockito;

public class OtherService {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public void otherServicePrint() {
        System.out.println("otherServicePrint");
    }

}
