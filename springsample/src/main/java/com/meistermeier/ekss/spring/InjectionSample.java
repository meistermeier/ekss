package com.meistermeier.ekss.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InjectionSample {

	private static final Logger LOG = LoggerFactory.getLogger(InjectionSample.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

		ClassA classA = context.getBean(ClassA.class);
		ClassB classB = context.getBean(ClassB.class);

		classA.doSth();
		classB.doSth();
	}
}
