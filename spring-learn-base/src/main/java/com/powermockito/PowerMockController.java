package com.powermockito;

public class PowerMockController {

    public String getName() {

        System.out.println("get name is called");
        return "powerMockController";
    }

    public String getStudentCount() {

        return getCount();
    }

    private String getCount() {

//        Integer.parseInt("a");
        System.out.println("get Count is called");
        return "count is 0";
    }

    public String getId() {
        return PowerMockService.generateId();
    }

}
