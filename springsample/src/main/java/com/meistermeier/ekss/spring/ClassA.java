package com.meistermeier.ekss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ClassA {

	private final ClassB classB;

	public final String name = "ClassA";

	@Autowired
	public ClassA(ClassB classB) {
		this.classB = classB;
	}

	public void doSth() {
		System.out.println(classB.name);
	}
}
