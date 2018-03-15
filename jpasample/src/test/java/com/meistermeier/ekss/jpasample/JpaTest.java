package com.meistermeier.ekss.jpasample;

import static com.meistermeier.ekss.jpasample.TestHelper.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JpaTest {
	private static final Logger LOG = LoggerFactory.getLogger(JpaTest.class);

	private SessionFactory sessionFactory;
	private Long userId;
	private User user;

	@Before
	public void setup() {
		this.sessionFactory = createSessionFactory();

		user = new User("Max", "Mustermann", 22);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
		userId = user.getId();

	}

	@After
	public void tearDown() {
		this.sessionFactory.close();
	}

	@Test
	public void load() {
		Session session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, userId);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}

	@Test
	public void loadInDifferentSession() {
		Session session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, userId);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}

	@Test
	public void updateAndLoadEntityInSession() {
		Session session = sessionFactory.openSession();

		user.setFirstName("Hans");

		session.update(user);

		User loadedUser = session.load(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
		session.close();
	}

	@Test
	public void updateAndLoadEntityInNewSession() {
		Session session = sessionFactory.openSession();

		user.setFirstName("Hans");

		session.update(user);
		session.close();
		session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
		session.close();
	}

	@Test
	public void deleteEntity() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(user);
		session.flush();
		transaction.commit();
		session.close();

		session = sessionFactory.openSession();
		User loadedUser = session.get(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
		session.close();
	}

}
