package com.meistermeier.springdatajpasample.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	//	@Modifying
	//	@Query("update User u set u.firstName=:newName where u.firstName=:name")
	//	int findByFirstName(@Param("name") String firstName, @Param("newName") String newName);
}
