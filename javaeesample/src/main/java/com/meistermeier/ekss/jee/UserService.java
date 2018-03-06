package com.meistermeier.ekss.jee;

import java.util.List;

import com.meistermeier.ekss.jee.user.User;

public interface UserService {

	List<User> loadAllUser();
}
