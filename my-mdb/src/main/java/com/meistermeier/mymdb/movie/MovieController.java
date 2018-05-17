package com.meistermeier.mymdb.movie;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies")
public class MovieController {

	private final MovieService movieService;

	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		Movie matrix = new Movie("Matrix <script>alert('hello');</script>");
		matrix.setGenre(Genre.SCIFI);
		Movie matrix2 = new Movie("Matrix II");
		matrix2.setGenre(Genre.SCIFI);
		Movie it = new Movie("It");
		it.setGenre(Genre.HORROR);

		List<Movie> movies = movieService.loadAllMovies();

		movies.add(matrix);
		movies.add(matrix2);
		movies.add(it);

		model.addAttribute("movies", movies);
		return "list";
	}

	@GetMapping("/random")
	public String randomMovie(Model model) {
		Movie randomMovie = movieService.getRandomMovie();
		if (randomMovie == null) {
			Movie matrix = new Movie("Matrix");
			matrix.setGenre(Genre.SCIFI);
			model.addAttribute("movie", matrix);
		} else {
			model.addAttribute("movie", randomMovie);
		}
		return "random";
	}

	@GetMapping("/create")
	MovieForm createMovie() {
		return new MovieForm();
	}

	@PostMapping("/save")
	public String save(@Valid MovieForm form, BindingResult bindingResult) throws Exception {
		String rv = "movies/createMovie";
		if (!bindingResult.hasErrors()) {

			Movie movie = new Movie(form.getTitle());
			movie.setReleaseDate(form.getReleaseDate());
			movieService.createMovie(movie);

			rv = "redirect:/movies/";
		}
		if (form.getTitle().contains("error")) {
			throw new Exception("alles kaputt");
		}
		return rv;

	}

	@GetMapping("/details")
	public String details(Model model, @RequestParam("movieId") Long id) {
		model.addAttribute("movie", movieService.findMovie(id));
		return "details";
	}

	@ExceptionHandler
	public String handleException(Exception exception) {
		// exception.printStackTrace();
		return "movies/error";
	}
}
