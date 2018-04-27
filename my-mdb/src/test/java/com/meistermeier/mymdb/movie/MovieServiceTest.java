package com.meistermeier.mymdb.movie;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieServiceTest {

	@Autowired
	private MovieService movieService;

	@Test
	public void createAndLoadMovie() throws Exception {
		Movie movie = new Movie();
		movie.setTitle("Matrix");
		movie.setCountry(new Country("USA"));
		movie.setSummary("A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.");
		movie.setReleaseDate(new DateFormatter().parse("11.6.1999", Locale.GERMAN));

		Movie savedMovie = movieService.createMovie(movie);

		assertThat(movieService.findOneMovie().getTitle(), equalTo(savedMovie.getTitle()));
	}
}
