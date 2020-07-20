package ues.projekat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages= {"ues.projekat.app.controller", "ues.projekat.service", "ues.projekat.app.repository", "ues.projekat.app.model"})
public class EmailClientApp extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmailClientApp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(EmailClientApp.class, args);
	}  
			
}