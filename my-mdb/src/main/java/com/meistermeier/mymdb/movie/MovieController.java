package com.meistermeier.mymdb.movie;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/movies")
public class MovieController {

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("movieList", Arrays.asList("Matrix", "Matrix II"));
		return "listView";
	}

	@GetMapping("/createMovie")
	MovieForm createMovie() {
		return new MovieForm();
	}

	@PostMapping("/save")
	public String save(@Valid MovieForm form, BindingResult bindingResult) throws Exception {
		String rv = "movies/createMovie";
		if (!bindingResult.hasErrors()) {
			System.out.println(form.getEmail().getMail());
			rv = "redirect:/movies/";
		}
		if (form.getEmail().getMail().contains("error")) {
			throw new Exception("alles kaputt");
		}
		return rv;

	}

	@ExceptionHandler
	public String handleException(Exception exception) {
		// exception.printStackTrace();
		return "movies/error";
	}
}
