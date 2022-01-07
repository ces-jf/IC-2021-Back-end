package br.com.uniacademia.cesIC.config.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

    public @Bean MongoClient mongoClient() {

	MongoClient mongoClient = MongoClients.create(
		"mongodb+srv://root:<password>@gerenciador-api.gvhvz.mongodb.net/ces-ic?retryWrites=true&w=majority");

	return mongoClient;
    }

    @Bean
    public CascadeMongoEventListener userCascadingMongoEventListener() {
	return new CascadeMongoEventListener();
    }

}
