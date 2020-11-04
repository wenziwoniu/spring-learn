/*@(#)HelloWorldController.java   2017年12月6日 
 * Copy Right 2017 vivebest Co.Ltd.
 * All Copyright Reserved
 */

package com.vivebest.spring.boot.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Document HelloWorldController
 * <p>
 * 
 * @version 1.0.0,2017年12月6日
 * @author zwnezheng
 * @since 1.0.0
 */

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @RequestMapping("/sayHello")
    public String home() {
        return "Hello World!";
    }
}
