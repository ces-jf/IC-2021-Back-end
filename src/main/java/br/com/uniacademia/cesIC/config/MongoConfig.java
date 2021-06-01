package br.com.uniacademia.cesIC.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	public @Bean MongoClient mongoClient() {
		
		MongoClient mongoClient = MongoClients.create("mongodb://root:root@localhost:27017/test?authSource=admin&authMechanism=SCRAM-SHA-1");
		
		return mongoClient;
	}

}
