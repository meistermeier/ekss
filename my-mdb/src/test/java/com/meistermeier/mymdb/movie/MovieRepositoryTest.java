package com.meistermeier.mymdb.movie;

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

		movieRepository.save(movie);

		System.out.println(movieRepository.findAll());
	}
}
