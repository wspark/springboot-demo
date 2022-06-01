package com.example.nhn.demo;


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
	
	@RequestMapping("/")
	public String home() {		
	  return "Hello Docker World deploy by tekton-v0.7";
	}


	@RequestMapping("/log")
	public String log() {	
		List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);	
		int lastNumber = numbersList.get(5);

		System.out.println("lastNumber =" + lastNumber);

		logger.trace("Trace Level 테스트");
        logger.debug("DEBUG Level 테스트");
        logger.info("INFO Level 테스트");
        logger.warn("Warn Level 테스트");
        logger.error("ERROR Level 테스트");
		
	  return "log test";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
