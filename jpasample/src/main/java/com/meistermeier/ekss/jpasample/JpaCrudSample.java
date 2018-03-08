package com.meistermeier.ekss.jpasample;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meistermeier.ekss.domain.user.User;

public class JpaCrudSample {

	private static final Logger LOG = LoggerFactory.getLogger(JpaCrudSample.class);

	private final SessionFactory sessionFactory;
	private Long userId;
	private User user;

	private JpaCrudSample() {
		this.sessionFactory = createSessionFactory();
	}

	public static void main(String[] args) {
		JpaCrudSample jpaCrudSample = new JpaCrudSample();

		jpaCrudSample.simpleSaveAndLoad();
		jpaCrudSample.loadInDifferentSession();
		jpaCrudSample.updateAndLoadEntityInSession();
		jpaCrudSample.updateAndLoadEntityInNewSession();
		jpaCrudSample.deleteEntity();

		jpaCrudSample.sessionFactory.close();
	}

	private void simpleSaveAndLoad() {
		Session session = sessionFactory.openSession();

		createUser(session);

		User loadedUser = session.load(User.class, userId);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}

	private void loadInDifferentSession() {
		Session session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, userId);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}

	private void updateAndLoadEntityInSession() {
		Session session = sessionFactory.openSession();

		user.setFirstName("Hans");

		session.update(user);

		User loadedUser = session.load(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
		session.close();
	}

	private void updateAndLoadEntityInNewSession() {
		Session session = sessionFactory.openSession();

		user.setFirstName("Hans");

		session.update(user);
		session.close();
		session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, userId);
		LOG.info("user found: {} ", loadedUser);
		session.close();
	}

	private void deleteEntity() {
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

	private void createUser(Session session) {
		user = new User("Max", "Mustermann", 22);
		session.save(user);

		userId = user.getId();
	}

	private SessionFactory createSessionFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:myDb");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.show_sql", "true");

		Configuration configuration = new Configuration()
				.addAnnotatedClass(User.class)
				.addProperties(properties);

		return configuration.buildSessionFactory();
	}
}
