package ues.projekat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages= {"ues.projekat.controller", "ues.projekat.service", "ues.projekat.repository"})
public class EmailClientApp extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmailClientApp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EmailClientApp.class, args);
	}  
			
}