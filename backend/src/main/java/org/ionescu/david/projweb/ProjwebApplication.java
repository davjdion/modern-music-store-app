package org.ionescu.david.projweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjwebApplication {

	private static final Logger log = LoggerFactory.getLogger(ProjwebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProjwebApplication.class, args);
	}

}
