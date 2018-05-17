package com.meistermeier.mymdb.movie;

import java.util.List;

public interface MovieService {

	Movie createMovie(Movie movie);

	Movie findOneMovie();

	List<Movie> loadAllMovies();

	Movie getRandomMovie();

	Movie findMovie(Long id);
}
