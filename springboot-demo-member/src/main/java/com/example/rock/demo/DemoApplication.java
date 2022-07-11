package com.example.rock.demo;


import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	

	@RequestMapping("/member")
	public String book() {	
		System.out.println("this is member service v0.1");
		logger.trace("Trace Level 테스트");
		logger.debug("DEBUG Level 테스트");
		logger.info("INFO Level 테스트");
		logger.warn("Warn Level 테스트");
		logger.error("ERROR Level 테스트");
		
	return "this is member service v0.1"; 
	}
		

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
