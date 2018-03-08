package com.meistermeier.ekss.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@ComponentScan("com.meistermeier.ekss.spring")
public class Application {

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean("singletonBean")
	public ScopedBean singletonBean() {
		return new ScopedBean();
	}

	@Bean("prototypeBean")
	@Scope("prototype")
	public ScopedBean prototypeBean() {
		return new ScopedBean();
	}

	@Bean
	@Profile("production")
	public ProfileSampleBean productionProfileSampleBean() {
		return () -> "Doing production ready work";
	}

	@Bean
	@Profile("testing")
	public ProfileSampleBean testProfileSampleBean() {
		return () -> "just here for the tests";
	}

}
