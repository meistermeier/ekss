package com.meistermeier.ekss.jpasample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hobby {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	public Hobby() {
	}

	public Hobby(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
