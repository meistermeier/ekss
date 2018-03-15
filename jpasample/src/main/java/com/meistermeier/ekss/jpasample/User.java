package com.meistermeier.ekss.jpasample;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private int age;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "User_Hobby", joinColumns = @JoinColumn(name = "User_id"), inverseJoinColumns = @JoinColumn(name = "hobbies_id"))
	private List<Hobby> hobbies = new ArrayList<>();

	public User() {
	}

	public User(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public Long getId() {
		return id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Hobby> getHobbies() {
		return hobbies;
	}

	@Override public String toString() {
		return firstName + " " + lastName + " (" + age + ")";
	}
}
