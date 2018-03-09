package com.meistermeier.ekss.spring.user;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

	@Override public List<User> getAllUser() {
		return Arrays.asList(new User("Max", "Mustermann", 22), new User("Melanie", "Musterfrau", 21));
	}
}
