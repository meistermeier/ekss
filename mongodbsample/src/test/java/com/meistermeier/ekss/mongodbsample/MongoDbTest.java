package com.meistermeier.ekss.mongodbsample;

import static com.mongodb.client.model.Updates.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDbTest {

	private static final String MIXED_COLLECTION = "mixedcollection";
	private static final String DATABASE_NAME = "test";
	private static MongodExecutable mongodExecutable;
	private static MongoClient mongo;

	@BeforeClass
	public static void initialize() throws Exception {
		MongodStarter starter = MongodStarter.getDefaultInstance();

		String bindIp = "localhost";
		int port = 12345;
		IMongodConfig mongodConfig = new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(bindIp, port, Network.localhostIsIPv6()))
				.build();

		mongodExecutable = starter.prepare(mongodConfig);
		mongodExecutable.start();

		MongoDbTest.mongo = new MongoClient(bindIp, port);
	}

	@Before
	public void createTestData() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);
		if (collection == null) {
		   	db.createCollection(MIXED_COLLECTION);
			collection = db.getCollection(MIXED_COLLECTION);
		}

		// mongodb shell: db.mixedcollection.insert({"myKey": "myValue"})
		collection.insertOne(new Document("myKey", "myValue"));
	}

	@After
	public void cleanUp() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
		db.getCollection(MIXED_COLLECTION);
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);

		// mongodb shell: db.mixedcollection.drop()
		collection.drop();
	}

	@AfterClass
	public static void stopInstance() {
		mongodExecutable.stop();
	}


	@Test
	public void listAllDataInCollection() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);

		// mongodb shell: db.getCollection("mixedCollection")
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);

		// mongodb shell: db.mixedcollection.find()
		for (Document document : collection.find()) {
			System.out.println("Found: " + document);
		}
	}

	@Test
	public void queryFilteredData() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);

		// add some more data
		collection.insertOne(new Document("myKey", "myValue").append("anotherKey", 10));
		collection.insertOne(new Document("myKey", "another value").append("anotherKey", 9));
		collection.insertOne(new Document("myKey", "42"));


		// mongodb shell: db.mixedcollection.find({"myKey": "myValue"})
		for (Document document : collection.find(new Document("myKey", "myValue"))) {
			System.out.println("Found (document equality): " + document);
		}

		// mongodb shell: db.mixedcollection.find({"anotherKey": { $gte: 10 } } )
		for (Document document : collection.find(Filters.and(Filters.gte("anotherKey", 10), Filters.lt("anotherKey", 20)))) {
			System.out.println("Found (gte): " + document);
		}
	}

	@Test
	public void updateDocument() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);

		Bson filter = new Document("myKey", "myValue");
		Bson updateValue = combine(
				set("myKey", "new value"),
				set("name", "Max Mustermann")
		);

		/* mongodb shell
		db.mixedcollection.update(
				{ "myKey" : "myValue" },
				{ $set: {
						"myKey": "new value",
						"name": "Max Mustermann"
						}
				}
		)
		*/
		collection.updateOne(filter, updateValue);

		for (Document document : collection.find()) {
			System.out.println("Found (updated): " + document);
		}
	}

	@Test
	public void removeDocument() {
		MongoDatabase db = mongo.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(MIXED_COLLECTION);

		Bson filter = new Document("myKey", "myValue");

		collection.deleteOne(filter);

		FindIterable<Document> documents = collection.find();
		for (Document document : documents) {
			System.out.println("Found (deleted): " + document);
		}

		assertThat(collection.count(), equalTo(0L));
	}

}
