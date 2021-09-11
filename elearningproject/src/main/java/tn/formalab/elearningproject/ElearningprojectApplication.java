package tn.formalab.elearningproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ElearningprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElearningprojectApplication.class, args);
	}

}
