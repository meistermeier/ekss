package com.meistermeier.ekss.jpasample;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meistermeier.ekss.jpasample.user.User;

public class JpaSample {

	private static final Logger LOG = LoggerFactory.getLogger(JpaSample.class);

	private final SessionFactory sessionFactory;
	private Long userId;
	private User user;

	public static void main(String[] args) {
		JpaSample jpaSample = new JpaSample();

		jpaSample.simpleSaveAndLoad();
		jpaSample.loadInDifferentSession();
		jpaSample.updateAndLoadEntityInSession();
		jpaSample.updateAndLoadEntityInNewSession();

		jpaSample.sessionFactory.close();
	}

	private JpaSample() {
		this.sessionFactory = createSessionFactory();
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
