package com.meistermeier.mymdb.movie;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceSpring implements MovieService {

	private final MovieRepository movieRepository;

	@Autowired
	public MovieServiceSpring(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional
	@Override public Movie createMovie(Movie movie) {
		return movieRepository.save(movie); // more complex logic
	}

	@Override
	public Movie findOneMovie() {
		return movieRepository.findAll().iterator().next();
	}
}
