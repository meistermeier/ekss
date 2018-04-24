package com.meistermeier.mymdb.movie;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String title;

	private Date releaseDate;

	@ManyToOne
	private Country country;

	@Enumerated(EnumType.STRING)
	private Genre genre;

	private Double rating;

	private String summary;

	@ManyToMany
	private List<Person> mainActors;

	@ManyToMany
	private List<Person> sideActors;

	@ManyToOne
	private Person producer;

	@ManyToOne
	private Person director;

	@ManyToMany
	private Set<Tag> tags;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Person> getMainActors() {
		return mainActors;
	}

	public void setMainActors(List<Person> mainActors) {
		this.mainActors = mainActors;
	}

	public List<Person> getSideActors() {
		return sideActors;
	}

	public void setSideActors(List<Person> sideActors) {
		this.sideActors = sideActors;
	}

	public Person getProducer() {
		return producer;
	}

	public void setProducer(Person producer) {
		this.producer = producer;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override public String toString() {
		return title;
	}
}
