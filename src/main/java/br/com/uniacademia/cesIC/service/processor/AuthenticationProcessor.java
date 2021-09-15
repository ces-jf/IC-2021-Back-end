package br.com.uniacademia.cesIC.service.processor;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uniacademia.cesIC.exception.authentication.alreadyExists.AuthenticationAlreadyExistsException;
import br.com.uniacademia.cesIC.exception.authentication.notChanged.AuthenticationNotChangedException;
import br.com.uniacademia.cesIC.exception.authentication.notFound.AuthenticationNotFoundException;
import br.com.uniacademia.cesIC.models.Authentication;
import br.com.uniacademia.cesIC.repositors.AuthenticationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthenticationProcessor {

	@Autowired
	private AuthenticationRepository authenticationRepository;

	public Authentication exists(Long id) {
		log.info("Start - AuthenticationProcessor.exists - Id {}", id);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findById(id);
		if (!optAuthentication.isPresent()) {
			log.error("AuthenticationNotFoundException - Id: {}", id);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationProcessor.exists - Authentication {}", optAuthentication.get());
		return optAuthentication.get();
	}

	public Authentication exists(String email) {
		log.info("Start - AuthenticationProcessor.exists - Email {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (!optAuthentication.isPresent()) {
			log.error("AuthenticationNotFoundException - Email: {}", email);
			throw new AuthenticationNotFoundException();
		}

		log.info("End - AuthenticationProcessor.exists - Authentication {}", optAuthentication.get());
		return optAuthentication.get();
	}

	public void alreadyExists(String email) {
		log.info("Start - AuthenticationProcessor.alreadyExists - Email {}", email);

		Optional<Authentication> optAuthentication = this.authenticationRepository.findByEmail(email);
		if (optAuthentication.isPresent()) {
			log.error("AuthenticationAlreadyExistsException - Email: {}", email);
			throw new AuthenticationAlreadyExistsException();
		}

		log.info("End - AuthenticationProcessor.alreadyExists");
	}

	public Authentication merge(Authentication authentication) {
		log.info("Start - AuthenticationProcessor.merge - Authentication: {}", authentication);

		Authentication original = this.exists(authentication.getId());
		original.setPassword(authentication.getPassword());

		if (Objects.equals(authentication, original)) {
			log.error("AuthenticationNotChangedException - Authentication: {}", authentication);
			throw new AuthenticationNotChangedException();
		}

		log.info("End - AuthenticationProcessor.merge - Authentication: {}", authentication);
		return authentication;
	}
}
