package com.meistermeier.ekss.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeSamples {

	private static final Logger LOG = LoggerFactory.getLogger(ScopeSamples.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

		LOG.info("singleton beans");
		context.getBean("singletonBean");
		context.getBean("singletonBean");
		context.getBean("singletonBean");
		context.getBean("singletonBean");

		LOG.info("prototype beans");
		context.getBean("prototypeBean");
		context.getBean("prototypeBean");
		context.getBean("prototypeBean");
		context.getBean("prototypeBean");
	}
}
