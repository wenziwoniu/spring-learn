package com.myself;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 程序启动入口
 * @author zwwang
 *
 */

//@ComponentScan
//@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatchApplication.class, args);
	}
}
