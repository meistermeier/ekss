package com.meistermeier.ekss.jee.user;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/user")
public class UserController {

	@Inject
	private UserService userService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listAllUsers() {
		return userService.loadAllUser();
	}
}
