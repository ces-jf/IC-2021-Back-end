package br.com.uniacademia.cesIC.service.processor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uniacademia.cesIC.exception.user.alreadyExists.UserAlreadyExistsException;
import br.com.uniacademia.cesIC.exception.user.notFound.UserNotFoundException;
import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserProcessor {

	@Autowired
	private UserRepository userRepository;

	public User exists(Long id) {
		log.info("Start - UserProcessor.exists - Id: {}", id);

		Optional<User> optUser = this.userRepository.findById(id);
		if (!optUser.isPresent()) {
			log.error("UserNotFoundException - Id: {}", id);
			throw new UserNotFoundException();
		}

		log.info("End - UserProcessor.exists - User: {}", optUser.get());
		return optUser.get();
	}

	public User exists(String name) {
		log.info("Start - UserProcessor.exists - NAME: {}", name);

		Optional<User> optUser = this.userRepository.findByName(name);
		if (!optUser.isPresent()) {
			log.error("UserNotFoundException - NAME: {}", name);
			throw new UserNotFoundException();
		}

		log.info("End - UserProcessor.exists - User: {}", optUser.get());
		return optUser.get();
	}

	public void alreadyExists(String name) {
		log.info("Start - UserProcessor.alreadyExists - NAME: {}", name);

		Optional<User> optUser = this.userRepository.findByName(name);
		if (optUser.isPresent()) {
			log.error("UserAlreadyExistsException - CPF: {}", name);
			throw new UserAlreadyExistsException();
		}

		log.info("End - UserProcessor.alreadyExists");
	}
}
