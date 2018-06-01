package com.meistermeier.ekss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassB {

	private ClassA classA;

	public final String name = "ClassB";

//	@Autowired
//	public ClassB(ClassA classA) {
//		this.classA = classA;
//	}

	@Autowired
	public void setClassA(ClassA classA) {
		this.classA = classA;
	}

	public void doSth() {
		System.out.println(classA.name);
	}
}
