package br.com.uniacademia.cesIC.repositors;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.uniacademia.cesIC.models.Authentication;

@Repository
public interface AuthenticationRepository extends MongoRepository<Authentication, Long> {

	@Transactional(readOnly = true)
	@Query("{'email':?0}")
	Optional<Authentication> findByEmail(String email);
}
