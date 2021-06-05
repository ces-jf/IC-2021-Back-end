package projeto.radasolidario.repositors;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import projeto.radasolidario.models.Authentication;

@Transactional(readOnly = true)
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

	public Optional<Authentication> findByEmail(String email);
}
