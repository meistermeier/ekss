package com.meistermeier.mymdb.person;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.meistermeier.mymdb.country.Country;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private Date birthday;

	@ManyToOne
	private Country birthplace;

	@OneToMany
	private Set<Award> awards;

	private String photo;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Country getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(Country birthplace) {
		this.birthplace = birthplace;
	}

	public Set<Award> getAwards() {
		return awards;
	}

	public void setAwards(Set<Award> awards) {
		this.awards = awards;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
