package br.com.uniacademia.cesIC.repositors;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.uniacademia.cesIC.models.User;

public interface RepositoryUser extends MongoRepository<User, String>{

}
