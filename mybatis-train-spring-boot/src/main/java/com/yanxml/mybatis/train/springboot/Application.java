package com.yanxml.mybatis.train.springboot;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
	"com.yanxml"
})
public class Application {
	
	public static Logger logger = LogManager.getLogger(Application.class); 
 	
	public static void main(String []args){
		try{
			SpringApplication application = new SpringApplication(Application.class);
			application.setWebEnvironment(true);
			application.run(args);
			logger.info("SpringBoot start successfully.");
			
		}catch(Exception e){
			logger.error(e);
		}
	}

}
