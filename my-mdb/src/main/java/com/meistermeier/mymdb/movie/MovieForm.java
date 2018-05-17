package com.meistermeier.mymdb.movie;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.meistermeier.mymdb.validation.NotMatrix;

public class MovieForm {

	@NotMatrix
	@NotEmpty
	private String title;

	@NotNull
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date releaseDate = new Date();

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

}
