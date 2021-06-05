package projeto.radasolidario.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import projeto.radasolidario.models.Authentication;
import projeto.radasolidario.repositors.AuthenticationRepository;

@Service
@Transactional(readOnly = true)
public class AuthenticationServiceImplementation implements UserDetailsService {

	@Autowired
	AuthenticationRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Authentication> user = repository.findByEmail(email);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Dados inválidos");
	}

}
