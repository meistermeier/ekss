package com.meistermeier.mymdb.movie;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Test
	public void createAndLoadMovie() {
		Movie movie = new Movie();
		movie.setTitle("Matrix");

		Movie savedMovie = movieRepository.save(movie);

		assertThat(movieRepository.findAll().iterator().next().getTitle(), equalTo(savedMovie.getTitle()));
	}
}
