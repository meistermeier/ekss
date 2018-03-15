package com.meistermeier.ekss.jpasample;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlSampleTest {

	private static final Logger LOG = LoggerFactory.getLogger(SqlSampleTest.class);

	private SessionFactory sessionFactory;
	private User firstUser;

	@Before
	public void setup() {
		this.sessionFactory = TestHelper.createSessionFactory();
		createUser();
	}

	private void createUser() {
		Session session = this.sessionFactory.openSession();
		firstUser = new User("Max", "Mustermann", 22);
		Transaction transaction = session.beginTransaction();
		session.persist(firstUser);
		session.persist(new User("Hans", "Mustermann", 22));
		session.persist(new User("Marie", "Mustermann", 20));
		session.persist(new User("Luise", "Mustermann", 21));

		transaction.commit();
		session.close();

	}

	@Test
	public void selectStatement() {
		runQuery("SELECT * FROM User");
	}

	@Test
	public void whereStatement() {
		runQuery("SELECT * FROM User WHERE AGE > 21");
	}

	@Test
	public void orderStatement() {
		runQuery("SELECT * FROM User ORDER BY AGE");
	}

	@Test
	public void orderDescStatement() {
		runQuery("SELECT * FROM User ORDER BY AGE DESC");
	}

	@Test
	public void groupStatement() {
		runQuery("SELECT age, count(*) FROM User GROUP BY AGE");
	}

	@Test
	public void joinStatement() {
		Hobby hobby = new Hobby("Musik hören");
 		saveHobby(hobby);
		runQuery("SELECT u.id, u.firstName, h.name from User u JOIN User_Hobby uh ON u.id=uh.User_id JOIN Hobby h on h.id=uh.hobbies_id");

	}

	private void saveHobby(Hobby hobby) {
		Session session = sessionFactory.openSession();

		Transaction transaction = session.beginTransaction();
		User loadedUser = session.load(User.class, firstUser.getId());
		loadedUser.getHobbies().add(hobby);
		session.persist(loadedUser);
		transaction.commit();
		session.close();

	}

	private void runQuery(String s) {
		Session session = this.sessionFactory.openSession();

		NativeQuery query = session.createNativeQuery(s);
		List list = query.getResultList();
		for (Object row : list) {
			LOG.info(Arrays.toString((Object[]) row));
		}

		session.close();
	}

}
