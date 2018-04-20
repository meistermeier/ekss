package com.meistermeier.springdatajpasample.user;

import java.applet.AudioClip;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuthorAuditorAware implements AuditorAware<Author> {
	@Override public Optional<Author> getCurrentAuditor() {
		return Optional.of(new Author("ich"));
	}
}
