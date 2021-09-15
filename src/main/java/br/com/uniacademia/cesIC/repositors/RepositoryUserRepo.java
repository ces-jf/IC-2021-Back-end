package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.UserRepo;

@Repository
public interface RepositoryUserRepo extends MongoRepository<UserRepo, String>{
	
	@Query("{'login':?0}")
	Optional<UserRepo> findByLogin(String login);

}
