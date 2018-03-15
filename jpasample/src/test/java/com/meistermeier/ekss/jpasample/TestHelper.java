package com.meistermeier.ekss.jpasample;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHelper {

	public static SessionFactory createSessionFactory() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.connection.url", "jdbc:h2:mem:myDb");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.show_sql", "true");

		Configuration configuration = new Configuration()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Hobby.class)
				.addProperties(properties);

		return configuration.buildSessionFactory();
	}
}
