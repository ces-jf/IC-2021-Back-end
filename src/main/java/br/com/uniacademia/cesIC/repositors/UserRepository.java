package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.uniacademia.cesIC.models.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    Optional<User> findByName(String name);
}
