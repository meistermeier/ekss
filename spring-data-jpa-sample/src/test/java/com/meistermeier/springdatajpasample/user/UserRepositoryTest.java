package com.meistermeier.springdatajpasample.user;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	private User savedUser;

	@Before
	public void createTestUser() {
		User user = new User("Max", "Mustermann", 32);
		savedUser = userRepository.save(user);
//		user = new User("Max", "Mustermann2", 32);
//		savedUser = userRepository.save(user);
	}

	@After
	public void clearDatabase() {
		userRepository.deleteAll();
	}


	@Test
	public void findByName() {
		System.out.println(userRepository.findByFirstName("Max"));
	}

	@Test
	public void findById() {
		User user = userRepository.findById(savedUser.getId()).get();
		System.out.println(user.getLastModified());
		System.out.println(user.getAuthor());
		assertThat(user.getFirstName(), equalTo("Max"));
		assertThat(user.getLastName(), equalTo("Mustermann"));
		assertThat(user.getAge(), equalTo(32));
	}

	@Test
	public void existsById() {
		boolean exists = userRepository.existsById(savedUser.getId());

		assertTrue(exists);
	}

	@Test
	public void delete() {
		userRepository.delete(savedUser);

		assertThat(userRepository.count(), equalTo(0L));
	}

	@Test
	public void deleteById() {
		userRepository.deleteById(savedUser.getId());

		assertThat(userRepository.count(), equalTo(0L));
	}

	@Test
	public void findAllUsers() {
		Iterable<User> all = userRepository.findAll();

		for (User user : all) {
			assertThat(user.getFirstName(), equalTo("Max"));
			assertThat(user.getLastName(), equalTo("Mustermann"));
			assertThat(user.getAge(), equalTo(32));
		}
	}

	@Test
	public void findAllByIds() {
		Iterable<User> all = userRepository.findAllById(Collections.singletonList(savedUser.getId()));

		for (User user : all) {
			assertThat(user.getFirstName(), equalTo("Max"));
			assertThat(user.getLastName(), equalTo("Mustermann"));
			assertThat(user.getAge(), equalTo(32));
		}
	}

}
