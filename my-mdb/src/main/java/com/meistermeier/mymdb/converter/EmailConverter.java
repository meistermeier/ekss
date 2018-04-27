package com.meistermeier.mymdb.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.meistermeier.mymdb.movie.Email;

@Component
public class EmailConverter implements Converter<String, Email> {

	@Override public Email convert(String s) {
		Email email = new Email();
		email.setMail(s);
		return email;
	}
}
