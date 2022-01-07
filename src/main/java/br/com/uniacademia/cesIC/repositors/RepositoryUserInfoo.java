package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.UserInfoo;

@Repository
public interface RepositoryUserInfoo extends MongoRepository<UserInfoo, String>{
	
	@Query("{'login':?0}")
	public Optional<UserInfoo> findByLogin(String login);

}
