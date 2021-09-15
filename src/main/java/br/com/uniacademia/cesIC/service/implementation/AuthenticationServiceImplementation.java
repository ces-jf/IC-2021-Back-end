package br.com.uniacademia.cesIC.service.implementation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.uniacademia.cesIC.constant.AuthenticationRole;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFPDTO;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationFRDTO;
import br.com.uniacademia.cesIC.dto.authentication.AuthenticationRPDTO;
import br.com.uniacademia.cesIC.dto.user.UserPIDTO;
import br.com.uniacademia.cesIC.models.Authentication;
import br.com.uniacademia.cesIC.models.User;
import br.com.uniacademia.cesIC.repositors.AuthenticationRepository;
import br.com.uniacademia.cesIC.repositors.UserRepository;
import br.com.uniacademia.cesIC.security.entity.JwtUser;
import br.com.uniacademia.cesIC.service.AuthenticationService;
import br.com.uniacademia.cesIC.service.processor.AuthenticationProcessor;
import br.com.uniacademia.cesIC.util.Encryptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImplementation implements AuthenticationService, UserDetailsService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AuthenticationProcessor authenticationProcessor;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public AuthenticationFRDTO findById(Long id) {
		log.info("Start - AuthenticationServiceImplementation.findById - Id: {}", id);

		Authentication authentication = this.authenticationProcessor.exists(id);
		AuthenticationFRDTO authenticationFRDTO = this.mapper.map(authentication, AuthenticationFRDTO.class);

		log.info("End - AuthenticationServiceImplementation.findById - AuthenticationFRDTO: {}", authenticationFRDTO);
		return authenticationFRDTO;
	}

	@Override
	public AuthenticationFRDTO findByEmail(String email) {
		log.info("Start - AuthenticationServiceImplementation.findByEmail - Email: {}", email);

		Authentication authentication = this.authenticationProcessor.exists(email);
		AuthenticationFRDTO authenticationFRDTO = this.mapper.map(authentication, AuthenticationFRDTO.class);

		log.info("End - AuthenticationServiceImplementation.findByEmail - AuthenticationFRDTO: {}",
				authenticationFRDTO);
		return authenticationFRDTO;
	}

	@Override
	public AuthenticationRPDTO include(UserPIDTO userPIDTO, List<AuthenticationRole> roles) {
		log.info("Start - AuthenticationServiceImplementation.include - UserPIDTO: {} - List<AuthenticationRole>: {}",
				userPIDTO, roles);

		Authentication authentication = this.mapper.map(userPIDTO, Authentication.class);
		authentication.setRole(roles);
		authentication.setIsLocked(false);

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication = this.authenticationRepository.save(authentication);
		
		User user = userRepository.findByName(authentication.getUser().getName()).get();
		user.setAuthentication(authentication);
		
		this.userRepository.save(user);

		AuthenticationRPDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRPDTO.class);
		authenticationRDTO.setPassword(userPIDTO.getAuthentication().getPassword());

		log.info("End - AuthenticationServiceImplementation.include - AuthenticationRDTO: {}", authenticationRDTO);
		return authenticationRDTO;
	}

	@Override
	public AuthenticationRPDTO edit(AuthenticationFPDTO authenticationFPDTO) {
		log.info("Start - AuthenticationServiceImplementation.edit - AuthenticationFPDTO: {}", authenticationFPDTO);

		Authentication authentication = this.mapper.map(authenticationFPDTO, Authentication.class);
		authentication = this.authenticationProcessor.merge(authentication);

		String encodedPassword = Encryptor.encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication = this.authenticationRepository.save(authentication);
		AuthenticationRPDTO authenticationRDTO = this.mapper.map(authentication, AuthenticationRPDTO.class);

		log.info("End - AuthenticationServiceImplementation.edit - AuthenticationFRPDTO: {}", authenticationRDTO);
		return authenticationRDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		log.info("Start - AuthenticationServiceImplementation.loadUserByUsername - Email: {}", email);

		AuthenticationFRDTO authentication = this.findByEmail(email);
		JwtUser jwtUser = this.mapper.map(authentication, JwtUser.class);

		log.info("End - AuthenticationServiceImplementation.loadUserByUsername - JwtUser: {}", jwtUser);
		return jwtUser;
	}
}
