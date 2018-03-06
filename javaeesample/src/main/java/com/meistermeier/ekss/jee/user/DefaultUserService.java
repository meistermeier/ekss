package com.meistermeier.ekss.jee.user;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.meistermeier.ekss.jee.UserService;

@ApplicationScoped
public class DefaultUserService implements UserService {

	public List<User> loadAllUser() {
		return Arrays.asList(new User("Max", "Mustermann"), new User("Melanie", "Musterfrau"));
	}
}
