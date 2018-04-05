package com.meistermeier.ekss.jpasample;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlEditSampleTest {

	private static final Logger LOG = LoggerFactory.getLogger(SqlEditSampleTest.class);

	private SessionFactory sessionFactory = TestHelper.createSessionFactory();

	@Test
	public void dataManipulation() {
		createUser();
		debug();
		updateUser();
		debug();
		deleteUser();
		debug();
	}

	private void createUser() {
		runQuery("INSERT into User (firstName, lastName, age, id) VALUES ('Klaus', 'Mustermann', 23, 1)");
		runQuery("INSERT into User (firstName, lastName, age, id) VALUES ('Michael', 'Mustermann', 23, 2)");
	}

	private void updateUser() {
		runQuery("UPDATE User SET firstName = 'Hans'");
//		runQuery("UPDATE User SET firstName = 'Hans' WHERE id = 1");
	}

	private void deleteUser() {
		runQuery("DELETE User");
//		runQuery("DELETE User WHERE id = 1");
	}


	private void debug() {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		NativeQuery query = session.createNativeQuery("SELECT * FROM User");
		List list = query.getResultList();
		for (Object row : list) {
			LOG.info(Arrays.toString((Object[]) row));
		}
		transaction.commit();

		session.close();
	}

	private void runQuery(String queryString) {
		Session session = this.sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		NativeQuery query = session.createNativeQuery(queryString);
		int updatedEntities = query.executeUpdate();
		LOG.info("updated {} entities", updatedEntities);
		transaction.commit();

		session.close();
	}

}
