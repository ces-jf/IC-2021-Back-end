package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.User;

@Repository
public interface RepositoryUser extends MongoRepository<User, String>{
	
	@Query("{'login':?0}")
	Optional<User> findByLogin(String login);

}
