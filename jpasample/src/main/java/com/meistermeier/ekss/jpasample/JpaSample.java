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

	public static void main(String[] args) {
		JpaSample jpaSample = new JpaSample();

		jpaSample.simpleSaveAndLoad();
		jpaSample.saveAndLoadInDifferentSessions();

		jpaSample.sessionFactory.close();
	}

	private JpaSample() {
		this.sessionFactory = createSessionFactory();
	}

	private void simpleSaveAndLoad() {
		Session session = sessionFactory.openSession();

		session.save(new User("Max", "Mustermann", 22));

		User loadedUser = session.load(User.class, 1L);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}


	private void saveAndLoadInDifferentSessions() {
		Session session = sessionFactory.openSession();

		session.save(new User("Max", "Mustermann", 22));

		session.close();

		session = sessionFactory.openSession();

		User loadedUser = session.load(User.class, 1L);

		LOG.info("user found: {} ", loadedUser);

		session.close();
	}

	private SessionFactory createSessionFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:myDb");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		Configuration configuration = new Configuration()
				.addAnnotatedClass(User.class)
				.addProperties(properties);

		return configuration.buildSessionFactory();
	}
}
