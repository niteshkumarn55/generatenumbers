package com.vmware.sample.generatenumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vmware")
public class GeneratenumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneratenumberApplication.class, args);
	}

}
