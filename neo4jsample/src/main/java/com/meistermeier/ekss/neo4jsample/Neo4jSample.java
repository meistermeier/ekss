package com.meistermeier.ekss.neo4jsample;

import java.io.File;
import java.nio.file.Files;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class Neo4jSample {

	private static GraphDatabaseService graphDatabaseService;

	public static void main(String[] args) throws Exception {
		File tempFile = Files.createTempDirectory("neo4j").toFile();

		graphDatabaseService = new GraphDatabaseFactory().
				newEmbeddedDatabaseBuilder(tempFile)
				.newGraphDatabase();

		// CREATE User
		executeQuery("CREATE (u:User{name:'Max'}) return u");

		// MATCH all nodes
		executeQuery("MATCH (n) RETURN n");

		// CREATE relationships for existing user
		executeQuery("MATCH (u:User{name:'Max'}) WITH u CREATE (u)-[:LIKES]->(h:Hobby{type:'Coding'})");

		// MATCH existing graph
		executeQuery("MATCH (u:User{name:'Max'})-[r:LIKES]->(h:hobby{type:'Coding'}) return u,r,h");

		// Update name property with SET
		executeQuery("MATCH (u:User{name:'Max'}) SET u.name='Hans' return u");

		// Add new property with SET
		executeQuery("MATCH (u:User{name:'Hans'}) SET u.profession='Software engineer' return u");

		// Remove property with SET to null
		executeQuery("MATCH (u:User{name:'Hans'}) SET u.profession=NULL return u");

		// Remove property with REMOVE
		executeQuery("MATCH (u:User{name:'Hans'}) REMOVE u.name return u");

		// Delete all the relationship
		executeQuery("MATCH (u:User)-[r:LIKES]->(:Hobby) DELETE r");

		// Delete all/the Hobby node
		executeQuery("MATCH (h:Hobby) DELETE h");

		// Check again which nodes exist
		executeQuery("MATCH (n) return n");

		// Remove everything
		executeQuery("MATCH (n) DETACH DELETE n");

		executeQuery("MATCH (n) return n");
		graphDatabaseService.shutdown();

	}

	private static void executeQuery(String query) {
		Transaction transaction = graphDatabaseService.beginTx();
		System.out.println(graphDatabaseService.execute(query).resultAsString());

		transaction.success();
	}
}
