package com.meistermeier.springdatajpasample.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private final String name;

	public Author() {
		this("leer");
	}

	public Author(String name) {
		this.name = name;
	}

	@Override public String toString() {
		return "Author: " + name;
	}
}
