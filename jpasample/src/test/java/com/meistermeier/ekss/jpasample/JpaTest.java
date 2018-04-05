package com.meistermeier.ekss.jpasample;

import static com.meistermeier.ekss.jpasample.TestHelper.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaTest {
	private static final Logger LOG = LoggerFactory.getLogger(JpaTest.class);

	private EntityManager entityManager = createEntityManager();
	private Long userId;
	private User user;

	@Before
	public void setup() {
		user = new User("Max", "Mustermann", 22);

		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		userId = user.getId();

	}

	@After
	public void tearDown() {
		this.entityManager.close();
	}

	@Test
	public void load() {

		User loadedUser = entityManager.find(User.class, userId);

		LOG.info("user found: {} ", loadedUser);

	}

	@Test
	public void loadInDifferentSession() {

		User loadedUser = entityManager.find(User.class, userId);

		LOG.info("user found: {} ", loadedUser);
	}

	@Test
	public void updateAndLoadEntityInSession() {

		user.setFirstName("Hans");
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();

		User loadedUser = entityManager.find(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
	}

	@Test
	public void deleteEntity() {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();

		User loadedUser = entityManager.find(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
	}

}
