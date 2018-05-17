package com.meistermeier.mymdb.movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	@Override public List<Movie> loadAllMovies() {
		List<Movie> movieList = new ArrayList<>();
		movieRepository.findAll().forEach(movie -> {
			movieList.add(movie);
		});
		return movieList;
	}

	@Override public Movie getRandomMovie() {
		List<Movie> movies = loadAllMovies();
		int size = movies.size();
		if (size == 0) {
			return null;
		}
		int randomIndex = new Random().nextInt(size);
		return movies.get(randomIndex);
	}

	@Override public Movie findMovie(Long id) {
		return movieRepository.findById(id).get(); // danger zone
	}
}
